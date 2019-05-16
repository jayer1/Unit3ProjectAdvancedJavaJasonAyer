package jayer1.u2.parkingapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinMaxTest {

    @Test
    public void testMinMax(){
        MinMax minMax = new MinMax(3);
        double result = minMax.getAmount();
        System.out.println(result);
        assertEquals("Didn't return the correct result", 5.00,result,0);

        MinMax minMax1 = new MinMax(4);
        double result1 = minMax1.getAmount();
        System.out.println(result1);
        assertEquals("Didn't return the correct result", 6.00,result1,0);

        MinMax minMax2 = new MinMax(15);
        double result2 = minMax2.getAmount();
        System.out.println(result2);
        assertEquals("Didn't return the correct result", 15.00,result2,0);

        MinMax minMax3 = new MinMax(15);
        double result3 = minMax3.getAmount();
        System.out.println(result3);
        assertEquals("Didn't return the correct result", 15.00,result3,0);




    }

}