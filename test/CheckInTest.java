import jayer1.u2.parkingapp.CheckIn;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class CheckInTest {

    @Test
    public void testCheckIn(){
        CheckIn checkedin = new CheckIn();
        int result = checkedin.setCheckinTime();
        System.out.println(result);
        assertTrue("Error, random is too high", 12 >= result);
        assertTrue("Error, random is too low",  7  <= result);
    }

}