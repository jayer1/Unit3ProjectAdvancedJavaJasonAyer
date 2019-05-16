package jayer1.u2.parkingapp;

public interface FeeStrategyFactory {
    
    FeeStrategy make(String feeName);
}
