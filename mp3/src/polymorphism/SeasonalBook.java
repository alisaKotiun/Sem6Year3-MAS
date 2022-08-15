package polymorphism;

import util.InvalidFieldException;

import java.time.LocalDateTime;

import static polymorphism.Season.*;

public class SeasonalBook extends Book{

    private static int taxCharge = 10; // in %
    private static int productionCharge = 150; // in %

    private Season season;

    public SeasonalBook(String ISBN, String name, LocalDateTime publishDate, double baseCost, Season season) throws InvalidFieldException {
        super(ISBN, name, publishDate, baseCost);
        setSeason(season);
    }

    // *** GETTERS

    public static int getTaxCharge() {
        return taxCharge;
    }

    public static int getProductionCharge() {
        return productionCharge;
    }

    public String getSeason() {
        return season.getName();
    }

    // *** SETTERS

    public static void setTaxCharge(int taxCharge) throws InvalidFieldException {
        if(taxCharge < 0){
            throw new InvalidFieldException("Tax charge value is invalid");
        }
        SeasonalBook.taxCharge = taxCharge;
    }

    public static void setProductionCharge(int productionCharge) throws InvalidFieldException {
        if(productionCharge < 0){
            throw new InvalidFieldException("Production charge value is invalid");
        }
        SeasonalBook.productionCharge = productionCharge;
    }

    public void setSeason(Season season) throws InvalidFieldException {
        if(season == null){
            throw new InvalidFieldException("Season value is invalid");
        }
        this.season = season;
    }

    // *** ADDITIONAL LOGIC

    @Override
    public double finalCost() {
        double taxValue = this.getBaseCost() * taxCharge / 100;
        double productionCostValue = this.getBaseCost() * productionCharge / 100;
        double cost = this.getBaseCost() + taxValue + productionCostValue;
        double seasonDiscount;

        switch (this.season){
            case FALL:
                seasonDiscount = cost * 0.1;
                break;
            case WINTER:
                seasonDiscount = cost * 0.3;
                break;
            case SPRING:
                seasonDiscount = cost * 0.05;
                break;
            case SUMMER:
                seasonDiscount = cost * 0.2;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.season);
        }

        return cost - seasonDiscount;
    }
}
