package jayer1.u2.parkingapp;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
* This is the program driver
* @author Jason Ayer
*/
public class ParkingAppDriver {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        int vehicleID = 0;
        int earlyTime = 0;
        int lateTime;
        int elapsedHours;
        double amount;
        int amountCheckedOut = 0;
        int lastID = 0;
        DecimalFormat df = new DecimalFormat("#.00");
        Ticket ticket = null;
        int randomPick;
        int randomPickFromArrayList;
        double nbr;

        List<Ticket> myTicketList = new ArrayList();
        // Call FileRead method in FileStorage to read the file into the arrayList
        FileStorage fileReader = new FileStorage();
        myTicketList = fileReader.FileRead(ticket);


        // arraylist for collecting which slot in arraylist is not checked out
        ArrayList<Integer> checkedOutVehicles = new ArrayList<Integer>();
        amountCheckedOut = getCheckedOutVehicles(checkedOutVehicles, myTicketList);

        //Display current list
        //displayAllTickets(myTicketList);
        while (true) {
            nbr = (double) Math.random();
            if (amountCheckedOut == 0 || nbr > 0.75) { // if there are existing tickets still checked out and random # is <= 0.75

                // Display main menu and get input, redisplay menu of input is not 1 or 3
                int mainMenuSelection = 0;
                do {
                    String message = "\n\nBest Value Parking Garage\n"
                            + "\n=========================\n"
                            + "\n1 – Check/In\n\n2 – Special Event\n\n3 – Close Garage\n\n=> ";
                    mainMenuSelection = getInt(message);

                } while (mainMenuSelection != 1 && mainMenuSelection != 2 && mainMenuSelection != 3);

                // 1 = check in, 3 = close garage
                if (mainMenuSelection == 1) { // CHECK IN
                    lastID = myTicketList.get(myTicketList.size() - 1).getVehicleID();
                    vehicleID = lastID + 1;
                    //vehicleID += 1;

                    // Run the CheckIn class methods
                    CheckIn checkin = new CheckIn();
                    earlyTime = checkin.setCheckinTime();
                    ticket = new Ticket(vehicleID, earlyTime, 0, 0.0, false, false);

                    // Add ticket object to arraylist
                    myTicketList.add(ticket);
                    amountCheckedOut = getCheckedOutVehicles(checkedOutVehicles, myTicketList);
                    
                } else if (mainMenuSelection == 2){ //Special Event
                    lastID = myTicketList.get(myTicketList.size() - 1).getVehicleID();
                    vehicleID = lastID + 1;
                   
                    ticket = new Ticket(vehicleID, 0, 0, 0.0, false, true); 
                    
                   // Add ticket object to arraylist
                    myTicketList.add(ticket);
                    
                    //FACTORY WAY
                    FeeStrategyFactory feeFactory = new FeeStrategyFactoryImpl();
                    FeeStrategy myFee = feeFactory.make("SpecialEvent");
                    amount = myFee.getAmount();

                    
                    // Display Special Event output
                    specialEventDisplay(df, amount, lastID, myTicketList);
                    
                    amountCheckedOut = getCheckedOutVehicles(checkedOutVehicles, myTicketList);
                    
                } else if (mainMenuSelection == 3) {  // CLOSE GARAGE
                    summarizeCloseGarage(myTicketList);
                    //Display current list
                    //displayAllTickets(myTicketList);
                    // Stop the program once you display the summary
                    break;
                }

            } else if (nbr <= 0.75) { //else from if amountcheckedout < 3

                // Display main menu and get input, redisplay menu of input if not 1 or 2
                int submitMenuSelection = 1;

                do {

                    String message = "\n\nBest Value Parking Garage\n"
                            + "\n=========================\n"
                            + "\n1 – Check/Out\n\n2 – Lost Ticket\n\n=> ";
                    submitMenuSelection = getInt(message);

                } while (submitMenuSelection != 1 && submitMenuSelection != 2);

                if (submitMenuSelection == 1) { // CHECK OUT

                    randomPickFromArrayList = 0;
                    randomPick = 0;

                    randomPickFromArrayList = getRandomPick(randomPickFromArrayList, amountCheckedOut, randomPick, checkedOutVehicles, myTicketList);

                    // Get the current ticket in the ArrayList and get its checkin time
                    ticket = myTicketList.get(randomPickFromArrayList);
                    earlyTime = ticket.getCheckInHour(); //added this line

                    //Run the CheckOut class methods
                    CheckOut checkout = new CheckOut();
                    lateTime = checkout.setCheckOutTime(); //FROM CheckOut class
                    elapsedHours = checkout.setCalcDuration(earlyTime, lateTime);//FROM CheckOut class
                    
                    //FACTORY WAY
                    FeeStrategyFactory feeFactory = new FeeStrategyFactoryImpl();
                    ((FeeStrategyFactoryImpl) feeFactory).setElapsedHours(elapsedHours);
                    FeeStrategy myFee = feeFactory.make("MinMax");
                    amount = myFee.getAmount();

                    
                    //Apply results from CheckOut class to current ticket
                    ticket.setCheckOutHour(lateTime);
                    ticket.setAmount(amount);

                    // Display the CheckOut receipt
                    checkOutDisplay(elapsedHours, randomPickFromArrayList, myTicketList);

                    amountCheckedOut = getCheckedOutVehicles(checkedOutVehicles, myTicketList);

                } else if (submitMenuSelection == 2) { // LOST TICKET
                    randomPickFromArrayList = 0;
                    randomPick = 0;

                    randomPickFromArrayList = getRandomPick(randomPickFromArrayList, amountCheckedOut, randomPick, checkedOutVehicles, myTicketList);

                    ticket = myTicketList.get(randomPickFromArrayList);
                    ticket.setLostTicket(true);

                    //FACTORY WAY
                    FeeStrategyFactory feeFactory = new FeeStrategyFactoryImpl();
                    FeeStrategy myFee = feeFactory.make("LostTicket");
                    amount = myFee.getAmount();


                    // Display lost ticket output
                    lostTicketDisplay(df, amount, randomPickFromArrayList, myTicketList);

                    // Update the checkedoutvehicles list
                    amountCheckedOut = getCheckedOutVehicles(checkedOutVehicles, myTicketList);
                }
            }
        }
        // Write the arraylist back to the file
        FileStorage fileWriter = new FileStorage();
        fileWriter.FileWrite(myTicketList);

    }

    /**
    * This gets a random element from the ArrayList
    * @param randomPickFromArrayList the first number
    * @param amountCheckedOut - what's returned from the getCheckedOutVehicles method
    * @param randomPick - what's returned from the pickRandomCheckOutVehicle method
    * @param checkedOutVehicles - list of vehicles that have no lost ticket true, special event true, or checkOutHour
    * @param myTicketList - main list of all vehicles
    * @return randomPickFromArrayList which represents a slot in the ArrayList
    */
    public static int getRandomPick(int randomPickFromArrayList, int amountCheckedOut, int randomPick, List<Integer> checkedOutVehicles, List<Ticket> myTicketList) {
        amountCheckedOut = getCheckedOutVehicles(checkedOutVehicles, myTicketList);
        if (amountCheckedOut == 1) { // If there's only one ticket that's not checked out...
            randomPickFromArrayList = checkedOutVehicles.get(0);
        } else { // otherwise get the list of tickets not checked out and pick a random one
            randomPick = pickRandomCheckOutVehicle(checkedOutVehicles, myTicketList);
            randomPickFromArrayList = randomPick;
        }
        return randomPickFromArrayList;
    }

    /**
    * This displays the passed in message and returns the response (typical sout + .nextInt() code)
    * @param message - message passed from the driver
    * @return response - returns integer value the user entered
    */
    private static int getInt(String message) {
        int response = 0;
        Scanner keyboard = new Scanner(System.in);
        System.out.print(message);
        response = keyboard.nextInt();
        keyboard.nextLine();
        return response;
    }

    /**
    * This displays the checkout summary to user after he/she checks out car from parking out
    * @param elapsedHours - difference between checkInHour and checkOutHour as calculated by setCalcDuration method in CheckOut class
    * @param nbr - number indicated the correct element in the ArrayList
    * @param myTicketList - main ArrayList of all tickets
    */
    public static void checkOutDisplay(int elapsedHours, int nbr, List<Ticket> myTicketList) {
        int start = myTicketList.get(nbr).getCheckInHour();
        int end = myTicketList.get(nbr).getCheckOutHour();
        DecimalFormat df = new DecimalFormat("#.00");
        String message = "\n\nBest Value Parking Garage\n\n=========================\n\nReceipt for vehicle ID "
                + myTicketList.get(nbr).getVehicleID() + "\n\n\n" + elapsedHours + " hours parked "
                + myTicketList.get(nbr).getCheckInHour() + " am - " + myTicketList.get(nbr).getCheckOutHour()
                + " pm" + "\n\n$" + df.format(myTicketList.get(nbr).getAmount());
        printMessage(message);
    }

    /**
    * This displays the Lost Ticket summary to user after he/she selects Lost Ticket from menu
    * @param df - number format
    * @param amount - standard amount charged for lost ticket
    * @param nbr - number indicated the correct element in the ArrayList
    * @param myTicketList - main ArrayList of all tickets
    */
    public static void lostTicketDisplay(DecimalFormat df, double amount, int nbr, List<Ticket> myTicketList) {
        String message = ("\nBest Value Parking Garage\n\n=========================\n\nReceipt for vehicle id " + myTicketList.get(nbr).getVehicleID()
                + "\n\n\nLost Ticket\n\n$" + df.format(amount) + "\n");
        printMessage(message);
    }
    
    /**
    * This displays the Special Event summary to user after he/she selects Special Event from menu
    * @param df - number format
    * @param amount - standard amount charged for lost ticket
    * @param nbr - number indicated the correct element in the ArrayList
    * @param myTicketList - main ArrayList of all tickets
    */
    public static void specialEventDisplay(DecimalFormat df, double amount, int nbr, List<Ticket> myTicketList) {
        String message = ("\nBest Value Parking Garage\n\n=========================\n\nReceipt for vehicle id " + myTicketList.get(nbr).getVehicleID()
                + "\n\n\nSpecial Event\n\n$" + df.format(amount) + "\n");
        printMessage(message);
    }

    /**
    * This cycles through the myTicketList ArrayList. For each element, if the CheckOutHour is 0 and the LostTicket and SpecialEvent booleans are false, it adds that element to a new list
    * Then it gets the size of the new list and returns that size
    * @param checkedOutVehicles - constructed list of tickets that can still be acted on
    * @param myTicketList - main ArrayList of all tickets
    * @return amountNotCheckedOut - number of elements in the new list (See description about)
    */
    public static int getCheckedOutVehicles(List<Integer> checkedOutVehicles, List<Ticket> myTicketList) {
        // ArrayList for collecting which slot in ArrayList is not checked out
        checkedOutVehicles.clear();

        // If getCheckOutHour() = 0, lostticket is false and specialevent is false, add index to checkedOutVehicles arraylist
        for (int i = 0; i < myTicketList.size(); i++) {
            if (myTicketList.get(i).getCheckOutHour() == 0 && myTicketList.get(i).getLostTicket() == false && myTicketList.get(i).getSpecialEvent()== false) {
                checkedOutVehicles.add(i);
            }
        }
        int amountNotCheckedOut = checkedOutVehicles.size();
        return amountNotCheckedOut;
    }

    /**
    * This cycles through the myTicketList ArrayList. For each element, if the CheckOutHour is 0 and the LostTicket and SpecialEvent booleans are false, it adds that element to a new list
    * Then it gets the size of the new list and returns that size
    * @param myTicketList - main ArrayList of all tickets
     *@param checkedOutVehicles - constructed list of tickets that can still be acted on
    * @return amountNotCheckedOut - number of elements in the new list (See description above)
    */
    public static int pickRandomCheckOutVehicle(List<Integer> checkedOutVehicles, List<Ticket> myTicketList) {
        // arraylist for collecting which slot in arraylist is not checked out
        checkedOutVehicles.clear();
        for (int i = 0; i < myTicketList.size(); i++) {
            // Lists all tickets pulled into the Arraylist from the file
            if (myTicketList.get(i).getCheckOutHour() == 0 && myTicketList.get(i).getLostTicket() == false && myTicketList.get(i).getSpecialEvent() == false) {
                checkedOutVehicles.add(i);
            }
        }

        // Randomly grab a slot in the list of checked out vehicles
        Random r = new Random();
        int amountCheckedOut = checkedOutVehicles.size() - 1; // One less than getCheckedOutVehicles
        int slot = r.nextInt((amountCheckedOut - 0) + 1) + 0;
        int chosenCheckedOutVehicle = checkedOutVehicles.get(slot);
        return chosenCheckedOutVehicle;
    }

    /**
    * This is what appears when the user closes the garage. It cycles through the myTicketList to count ticket objects that are checked out, lost ticket and special events. Then it displays the results.
    * @param myTicketList - main ArrayList of all tickets
    * 
    */
    public static void summarizeCloseGarage(List<Ticket> myTicketList) throws IOException, ClassNotFoundException {
        DecimalFormat df = new DecimalFormat("#.00");
        double amount = 0;
        int lostCount = 0;
        int specialCount = 0;
        double sumLostCount = 0;
        double sumSpecialCount = 0;
        double total = 0;
        int countCheckIns = 0;

        for (int i = 0; i < myTicketList.size(); i++) {
            amount += myTicketList.get(i).getAmount();
            if (myTicketList.get(i).getLostTicket() == true) {
                lostCount++;
            }
            if (myTicketList.get(i).getSpecialEvent()== true) {
                specialCount++;
            }
            if (myTicketList.get(i).getCheckInHour() > 0 && myTicketList.get(i).getCheckOutHour() > 0) {
                countCheckIns++;
            }

        }

        // Grabbing the factory/strategy values for lost ticket and special event fees
        FeeStrategyFactory feeFactory = new FeeStrategyFactoryImpl();
        FeeStrategy myLostFee = feeFactory.make("LostTicket");
        double lostMultiplier = myLostFee.getAmount();

        FeeStrategyFactory feeFactory2 = new FeeStrategyFactoryImpl();
        FeeStrategy mySpecialEventFee = feeFactory2.make("SpecialEvent");
        double SEMultiplier = mySpecialEventFee.getAmount();

        sumLostCount = lostCount * lostMultiplier;
        sumSpecialCount = specialCount * SEMultiplier;
        total = amount + sumLostCount + sumSpecialCount;
        String message = "\nBest Value Parking Garage\n\n=========================\n\nActivity to Date\n\n\n$" + df.format(amount) + " was collected from " + countCheckIns + " Check Ins\n\n$" + df.format(sumLostCount) + " was collected from " + lostCount + " Lost Tickets\n\n$"  + df.format(sumSpecialCount) + " was collected from " + specialCount + " Special Events\n\n$" + df.format(total) + " was collected overall\n";

        printMessage(message);
    }

    /**
    * This basically prints a message to the console. When other methods want to print to the console, they pass their own message and this prints it out
    * @param message - string built from calling program is sent here
    */
    public static void printMessage(String message) {
        System.out.println(message);
    }

    /**
    * This is for testing purposes only. It cycles through all tickets in the myTicketList and displays all attributes of each element
    * @param myTicketList - main ArrayList of all tickets
    * 
    */
    public static void displayAllTickets(List<Ticket> myTicketList) {
        String message = "";
        for (int i = 0; i < myTicketList.size(); i++) {
            message += "vehicle " + myTicketList.get(i).getVehicleID() + " checkin " + myTicketList.get(i).getCheckInHour()
                    + " am checkout " + myTicketList.get(i).getCheckOutHour() + " amount collected at checkout " + myTicketList.get(i).getAmount() + " Lost Ticket? " + myTicketList.get(i).getLostTicket()  + " Special Event? " + myTicketList.get(i).getSpecialEvent() + "\n";
        }
        System.out.println("Here's the list of tickets:\n" + message);
    }

}
