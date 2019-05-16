package jayer1.u2.parkingapp;

/**
* This is the Calculator interface used in conjunction with the below classes as a strategy design pattern
* Was omitted from the program when the Fee Strategy interface was added
*
*/
/*public interface Calculates {
    
    double calculate();
    
    
}*/

/**
 * This designates the calculation will be MinMax and performs the getAmount calculation based on the elapsedHours
 */
class MinMax implements FeeStrategy{
    
    private int elapsedHours;

    /**
     * This designates the calculation will be MinMax and performs the getAmount calculation based on the elapsedHours
     * @param elapsedHours - amount of time between checkin and checkout
     */
    public MinMax(int elapsedHours){
        this.elapsedHours = elapsedHours;
            
    }

    /**
     * The getAmount calculation takes the elapsedHours, performs a calculation and returns the amount to charge
     * @return amount - amount to charge for the time-based parking ticket (checkin/checkout)
     */
    @Override
    public double getAmount(){
        double amount = 0;
        
        if (elapsedHours <= 3) {
            amount = 5;
        } else if (elapsedHours > 3 && elapsedHours <= 24) {
            amount = 5 + (elapsedHours - 3);
            if (amount > 15) {
                amount = 15;
            }
        }
        return amount;
    }
}

/**
 * This designates the calculation will be SpecialEvent and performs the getAmount calculation which returns 20.00
 */
class SpecialEvent implements FeeStrategy{

    /**
     * This designates the calculation will be MinMax and performs the getAmount calculation based on the elapsedHours
     * @return returns 20.00
     */
    @Override
    public double getAmount() {
        return 20.00;
    }
    
    private static SpecialEvent single_instance = null;

    /**
     * This singleton forces only one instance of SpecialEvent to be created, otherwise, use the existing one
     * @return single_instance - the SpecialEvent instance
     */
    public static SpecialEvent getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new SpecialEvent(); 
  
        return single_instance; 
    } 
    
}

/**
 * This designates the calculation will be SpecialEvent and performs the getAmount calculation which returns 25.00
 */
class LostTicket implements FeeStrategy{

    /**
     * This getAmount method simply return 25.00
     * @return returns 25.00
     */
    @Override
    public double getAmount() {
        return 25.00;
    }
    
}

