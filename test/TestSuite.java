import jayer1.u2.parkingapp.LostTicketTest;
import jayer1.u2.parkingapp.MinMaxTest;
import jayer1.u2.parkingapp.SpecialEventTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses (  {
        CheckInTest.class,
        CheckOutTest.class,
        TicketTest.class,
        SpecialEventTest.class,
        LostTicketTest.class,
        MinMaxTest.class,
        FeeStrategyFactoryImplTest.class
}  )
public class TestSuite {
}
