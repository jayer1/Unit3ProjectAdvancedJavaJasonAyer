package jayer1.u2.parkingapp;

import java.util.Random;

/**
* This class handles the CheckOut functionality of setting a check out time and calculating the difference between the check in and check out time
*/
public class CheckOut {

    private static final int LOW = 1;
    private static final int HIGH = 11;
    private static final int TWELVE = 12;
    private int duration = 0;

    public CheckOut() {
        //LOW = this.LOW;
        //HIGH = this.HIGH;
    }

    /**
    * This sets a check out time by using a random number
    * @return resultHour - the random number picked to be designated as the check out time which will be stored as checkOutHour in the Ticket object
    */
    public int setCheckOutTime() {

        Random hour = new Random();
        int resultHour = hour.nextInt(HIGH - LOW) + LOW;
        return resultHour;
    }
    
    /**
    * This calculates the duration in time between when the ticket was checked in (from CheckIn class) and when it was checked out (from setCheckOutTime())
    * @param early - when the ticket was checked in
    * @param late - when the ticket was checked out
    * @return duration - the duration in time between when the ticket was checked in and when it was checked out
    */
    public int setCalcDuration(int early, int late) {

        return duration = TWELVE - early + late;

    }
}
