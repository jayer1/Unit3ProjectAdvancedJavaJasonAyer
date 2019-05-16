package jayer1.u2.parkingapp;

import jayer1.u2.parkingapp.SpecialEvent;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpecialEventTest {

    @Test
    public void testSpecialEvent(){
        SpecialEvent special = SpecialEvent.getInstance();
        double result = special.getAmount();
        System.out.println(result);
        assertEquals("Didn't return the correct result", 20.00,result,0);
    }

}