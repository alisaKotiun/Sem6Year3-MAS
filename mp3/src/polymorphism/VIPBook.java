package polymorphism;

import util.InvalidFieldException;

import java.time.LocalDateTime;

public class VIPBook extends Book{

    private static int productionCharge = 300; // in %

    private String description;

    public VIPBook(String ISBN, String name, LocalDateTime publishDate, double baseCost, String description) throws InvalidFieldException {
        super(ISBN, name, publishDate, baseCost);
        setDescription(description);
    }

    // *** GETTERS

    public static int getProductionCharge() {
        return productionCharge;
    }

    public String getDescription() {
        return description;
    }

    // *** SETTERS

    public static void setProductionCharge(int productionCharge) throws InvalidFieldException {
        if(productionCharge < 200){
            throw new InvalidFieldException("Production charge value is invalid");
        }
        VIPBook.productionCharge = productionCharge;
    }

    public void setDescription(String description) throws InvalidFieldException {
        if(description == null || description.isEmpty()){
            throw new InvalidFieldException("Desc field is mandatory");
        }
        this.description = description;
    }

    @Override
    public double finalCost() {
        return this.getBaseCost() + (this.getBaseCost() * productionCharge / 100);
    }
}
