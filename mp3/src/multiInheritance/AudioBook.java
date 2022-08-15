package multiInheritance;

import util.InvalidFieldException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AudioBook extends Book implements IAudio {

    private Worker speaker;
    private LocalDateTime productionDate;
    private double CDPrice;

    public AudioBook(String ISBN, LocalDateTime publishDate, Worker author, String name, double baseCost, Worker speaker, LocalDateTime productionDate, double CDPrice) throws InvalidFieldException {
        super(ISBN, publishDate, author, name, baseCost);
        setSpeaker(speaker);
        setProductionDate(productionDate);
        setCDPrice(CDPrice);
    }

    // *** GETTERS

    public Worker getSpeaker() {
        return speaker;
    }

    public LocalDateTime getProductionDate() {
        return productionDate;
    }

    public double getCDPrice() {
        return CDPrice;
    }

    // *** SETTERS

    public void setSpeaker(Worker speaker) throws InvalidFieldException {
        if(speaker == null) {
            throw new InvalidFieldException("Author field is mandatory");
        }
        this.speaker = speaker;
    }

    public void setProductionDate(LocalDateTime productionDate) throws InvalidFieldException {
        if(productionDate == null || productionDate.isAfter(LocalDateTime.now())) {
            throw new InvalidFieldException("Production Date field is mandatory");
        }
        this.productionDate = productionDate;
    }

    public void setCDPrice(double CDPrice) throws InvalidFieldException {
        if(CDPrice < 0) {
            throw new InvalidFieldException("Invalid value");
        }
        this.CDPrice = CDPrice;
    }

    // *** ADDITIONAL LOGIC

    @Override
    public double finalCost() {
        return this.getBaseCost() + this.getBaseCost() * Book.getProductionCharge() / 100 +
                this.getBaseCost() * IAudio.getCharge() / 100 ;
    }

    @Override
    public Map<String, Double> allCosts() {
        Map<String, Double> prices = new HashMap<>();
        prices.put("Audio", finalCost());
        prices.put("CD", CDPrice);
        return prices;
    }
}
