package multiInheritance;

import util.InvalidFieldException;

public abstract class Product {
    private static int counter = 1;

    private int code;
    private String name;
    private double baseCost;

    public Product(String name, double baseCost) throws InvalidFieldException {
        setCode();
        setName(name);
        setBaseCost(baseCost);
    }

    // *** GETTERS

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    protected double getBaseCost() {
        return baseCost;
    }

    // *** SETTERS

    private void setCode() {
        this.code = counter;
        counter += 1;
    }

    public void setName(String name) throws InvalidFieldException {
        if(name == null || name.isEmpty()){
            throw new InvalidFieldException("Name field is mandatory");
        }
        this.name = name;
    }

    public void setBaseCost(double baseCost) throws InvalidFieldException {
        if(baseCost < 0) {
            throw new InvalidFieldException("Invalid value");
        }
        this.baseCost = baseCost;
    }

    // *** ADDITIONAL LOGIC

    public abstract double finalCost();
}
