package polymorphism;

import util.InvalidFieldException;

import java.time.LocalDateTime;

public class EBook extends Book{

    private static int taxCharge = 2; // in %
    private static int productionCharge = 100; // in %

    private String format; // pdf, epub ...

    public EBook(String ISBN, String name, LocalDateTime publishDate, double baseCost, String format) throws InvalidFieldException {
        super(ISBN, name, publishDate, baseCost);
        setFormat(format);
    }

    // *** GETTERS

    public static int getTaxCharge() {
        return taxCharge;
    }

    public String getFormat() {
        return format;
    }

    public static int getProductionCharge() {
        return productionCharge;
    }

    // *** SETTERS

    public static void setTaxCharge(int taxCharge) throws InvalidFieldException {
        if(taxCharge < 0){
            throw new InvalidFieldException("Tax charge value is invalid");
        }
        EBook.taxCharge = taxCharge;
    }

    public static void setProductionCharge(int productionCharge) throws InvalidFieldException {
        if(productionCharge < 0){
            throw new InvalidFieldException("Production charge value is invalid");
        }
        EBook.productionCharge = productionCharge;
    }

    public void setFormat(String format) throws InvalidFieldException {
        if(format == null || format.isEmpty()){
            throw new InvalidFieldException("Format field is mandatory");
        }
        this.format = format;
    }

    // *** ADDITIONAL LOGIC

    @Override
    public double finalCost() {
        double taxValue = this.getBaseCost() * taxCharge / 100;
        double productionCostValue = this.getBaseCost() * productionCharge / 100;
        return this.getBaseCost() + taxValue + productionCostValue;
    }
}
