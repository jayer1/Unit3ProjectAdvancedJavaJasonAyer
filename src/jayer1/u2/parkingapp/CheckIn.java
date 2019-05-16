package jayer1.u2.parkingapp;

import java.util.Random;

/**
* This class handles creating a random check in time
*/
public class CheckIn {

    private static final int LOW = 7;
    private static final int HIGH = 12;

    public CheckIn() {
        //LOW = this.LOW;
        //HIGH = this.HIGH;
    }

    /**
    * This handles creating a random check in time which is an integer between the LOW and HIGH static values listed above
    * @return resultHour - the random number picked to be designated as the check in time
    */
    public int setCheckinTime() {

        Random hour = new Random();
        int resultHour = hour.nextInt(HIGH - LOW) + LOW;
        return resultHour;
    }
}
