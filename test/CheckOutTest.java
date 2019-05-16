import jayer1.u2.parkingapp.CheckOut;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class CheckOutTest {
    @Test
    public void testCheckOut(){
        CheckOut checkedOut = new CheckOut();
        int result = checkedOut.setCheckOutTime();
        int resultDuration = checkedOut.setCalcDuration(8, 2);

        System.out.println(result);
        System.out.println(resultDuration);
        assertEquals("Duration is calculated wrong", 6, resultDuration, 0);
        assertTrue("Error, random is too high", 11 >= result);
        assertTrue("Error, random is too low",  1  <= result);
    }
}