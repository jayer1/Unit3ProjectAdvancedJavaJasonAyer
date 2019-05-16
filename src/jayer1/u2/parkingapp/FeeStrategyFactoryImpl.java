package jayer1.u2.parkingapp;

/**
 * This class determines which calculation class to pick based on the make method from FeeStrategy
 * This is part of the abstract factory
 */
public class FeeStrategyFactoryImpl implements FeeStrategyFactory{
    
    private int elapsedHours;
    private double amount;

    /**
     * This just returns the amount
     * @return amount calculated in either MinMax, SpecialEvent or LostTicket
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount calculated by MinMax, SpecialEvent or LostTicket
     * @param amount calculated in either MinMax, SpecialEvent or LostTicket
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * * This just returns the elapsedHours
     * @return elapsedHours calculated elsewhere in the program
     */
    public int getElapsedHours() {
        return elapsedHours;
    }

    /**
     * * This sets the elapsedHours
     * @param elapsedHours calculated elsewhere in the program and passes it to the MinMax instance
     */
    public void setElapsedHours(int elapsedHours) {
        this.elapsedHours = elapsedHours;
    }

    /**
     * * This takes the feeName string and decides which class to create (MinMax, SpecialEvent or LostTicket)
     * @param feeName sent from the program to tell this method which class to instantiate
     * @return MinMax,SpecialEvent, LostTicket of null - basically returns an instantiated class or null
     */
    @Override
    public FeeStrategy make(String feeName) {

        switch(feeName){
            
            case "MinMax":
                return new MinMax(elapsedHours);
                
            case "SpecialEvent":
                return SpecialEvent.getInstance();
                
            case "LostTicket":
                return new LostTicket();
        }
        return null;
    
    }
}