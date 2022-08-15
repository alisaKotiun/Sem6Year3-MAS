package multiInheritance;

import java.util.Map;

public interface IAudio {
    static int streamingCharge = 10;

    double finalCost();
    Map<String, Double> allCosts(); //for CD and Audio

    static int getCharge(){
        return streamingCharge;
    }
}
