import jayer1.u2.parkingapp.Ticket;
import org.junit.Test;

import static org.junit.Assert.*;

public class TicketTest {

    @Test
    public void testTicket(){
        Ticket newTicket = new Ticket(12, 10, 3, 0, false, false);
        newTicket.setCheckInHour(9);
        newTicket.setCheckOutHour(2);
        newTicket.setAmount(15);
        newTicket.setLostTicket(true);
        newTicket.setSpecialEvent(true);

        double resultAmount = newTicket.getAmount();
        int resultCheckInHour = newTicket.getCheckInHour();
        int resultCheckOutHour = newTicket.getCheckOutHour();
        boolean resultLostTicket = newTicket.getLostTicket();
        boolean resultSpecialEvent = newTicket.getSpecialEvent();
        System.out.println("Check in " + resultCheckInHour + " check out " + resultCheckOutHour);
        assertTrue("LostTicket not set to true",resultLostTicket);
        assertTrue("SpecialEvent not set to true", resultSpecialEvent);
        assertEquals("CheckInHour not set to 9", resultCheckInHour, 9, 0);
        assertEquals("CheckOutHour not set to 2", resultCheckOutHour, 2, 0);
        assertEquals("Amount not set to 15", resultAmount, 15, 0);


    }

}