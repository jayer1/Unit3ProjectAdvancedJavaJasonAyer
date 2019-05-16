package jayer1.u2.parkingapp;

import jayer1.u2.parkingapp.LostTicket;
import org.junit.Test;

import static org.junit.Assert.*;

public class LostTicketTest {

    @Test
    public void testLostTickets(){

        LostTicket lostit = new LostTicket();
        double result = lostit.getAmount();
        System.out.println(result);
        assertEquals("Didn't return the correct result", 25.00,result,0);
    }

}