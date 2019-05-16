package jayer1.u2.parkingapp;

import java.io.Serializable;
import java.util.ArrayList;


/**
* This forms the main object for the project a ticket for the parking lot. The properties of the ticket are a vehicle ID, a check in and check out hour, the amount charged for a normal ticket when the car is checked out of the garage
* plus a couple booleans for lost ticket and special event
*/
public class Ticket implements Serializable {

    private int vehicleID;
    private int checkInHour;
    private int checkOutHour;
    private double amount;
    private boolean lostTicket;
    private boolean specialEvent;

    /**
    * This is the ticket constructor
    * @param vehicleID
    * @param checkInHour - number indicating when car was checked into the garage
    * @param checkOutHour - number indicating when car was checked out of the garage
    * @param amount - calculated amount charged for when a normal ticket is checked in and out
    * @param lostTicket - whether or not the ticket was a lost ticket
    * @param specialEvent - whether or not the ticket was a special event
    * @return duration - the duration in time between when the ticket was checked in and when it was checked out
    */
    public Ticket(int vehicleID, int checkInHour, int checkOutHour, double amount, boolean lostTicket, boolean specialEvent) {
        this.vehicleID = vehicleID;
        this.checkInHour = checkInHour;
        this.checkOutHour = checkOutHour;
        this.amount = amount;
        this.lostTicket = lostTicket;
        this.specialEvent = specialEvent;
    }

    /**
     * This is generic ticket constructor
     */
    public Ticket() {

    }

    /**
     * Getter for vehicleID
     * @returns vehicleID
     */
    public int getVehicleID() {
        return vehicleID;
    }

    /**
     * Setter for vehicleID
     * @param vehicleID
     */
    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    /**
     * Getter for checkInHour
     * @returns checkInHour
     */
    public int getCheckInHour() {
        return checkInHour;
    }

    /**
     * Setter for checkInHour
     * @param checkInHour
     */
    public void setCheckInHour(int checkInHour) {
        this.checkInHour = checkInHour;
    }

    /**
     * Getter for checkOutHour
     * @returns checkOutHour
     */
    public int getCheckOutHour() {
        return checkOutHour;
    }

    /**
     * Setter for checkInHour
     * @param checkOutHour
     */
    public void setCheckOutHour(int checkOutHour) {
        this.checkOutHour = checkOutHour;
    }

    /**
     * Getter for amount
     * @returns amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Setter for amount
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Getter for lostTicket
     * @returns lostTicket
     */
    public boolean getLostTicket() {
        return lostTicket;
    }

    /**
     * Setter for lostTicket
     * @param lostTicket
     */
    public void setLostTicket(boolean lostTicket) {
        this.lostTicket = lostTicket;
    }

    /**
     * Getter for specialEvent
     * @returns specialEvent
     */
    public boolean getSpecialEvent() {
        return specialEvent;
    }

    /**
     * Setter for specialEvent
     * @param specialEvent
     */
    public void setSpecialEvent(boolean specialEvent) {
        this.specialEvent = specialEvent;
    }


    public double calcAmount(int elapsedHours) {
        double amount = 0;
        if (elapsedHours <= 3) {
            amount = 5.00;
        } else if (elapsedHours > 3 && elapsedHours <= 24) {
            amount = 5 + (elapsedHours - 3);
            if (amount > 15) {
                amount = 15;
            }
        }
        return amount;
    }
}
