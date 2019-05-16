import jayer1.u2.parkingapp.FeeStrategy;
import jayer1.u2.parkingapp.FeeStrategyFactory;
import jayer1.u2.parkingapp.FeeStrategyFactoryImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class FeeStrategyFactoryImplTest {

    @Test
    public void testFeeStrategyFactoryImpl(){
        int elapsedHours = 8;
        FeeStrategyFactory feeFactory = new FeeStrategyFactoryImpl();
        ((FeeStrategyFactoryImpl) feeFactory).setElapsedHours(elapsedHours);
        FeeStrategy myFeeMinMax = feeFactory.make("MinMax");
        double result = myFeeMinMax.getAmount();
        System.out.println(result);
        assertEquals("Didn't return the correct MinMax result", 10.00,result,0);

        FeeStrategyFactory feeFactory2 = new FeeStrategyFactoryImpl();
        FeeStrategy myFeeSpecialEvent = feeFactory2.make("SpecialEvent");
        double result1 = myFeeSpecialEvent.getAmount();
        System.out.println(result1);
        assertEquals("Didn't return the correct Special Event result", 20.00,result1,0);

        FeeStrategyFactory feeFactory3 = new FeeStrategyFactoryImpl();
        FeeStrategy myFeeLostTicket = feeFactory3.make("LostTicket");
        double result2 = myFeeLostTicket.getAmount();
        System.out.println(result2);
        assertEquals("Didn't return the correct result", 25.00,result2,0);
    }

}