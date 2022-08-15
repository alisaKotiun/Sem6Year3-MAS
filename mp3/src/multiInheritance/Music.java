package multiInheritance;

import util.InvalidFieldException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Music extends Product implements IAudio{
    private static int recordingCost = 20;

    private String album;
    private LocalDateTime productionDate;
    private String producer;
    private double analogDiscPrice;

    public Music(String name, double baseCost, String album, LocalDateTime productionDate, String producer, double analogDiscPrice) throws InvalidFieldException {
        super(name, baseCost);
        setAlbum(album);
        setProductionDate(productionDate);
        setProducer(producer);
        setAnalogDiscPrice(analogDiscPrice);
    }

    // *** GETTERS

    public String getAlbum() {
        return album;
    }

    public LocalDateTime getProductionDate() {
        return productionDate;
    }

    public String getProducer() {
        return producer;
    }

    // *** SETTERS

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setProductionDate(LocalDateTime productionDate) throws InvalidFieldException {
        if(productionDate == null || productionDate.isAfter(LocalDateTime.now())) {
            throw new InvalidFieldException("Production Date field is mandatory");
        }
        this.productionDate = productionDate;
    }

    public void setProducer(String producer) throws InvalidFieldException {
        if(producer == null || producer.isEmpty()){
            throw new InvalidFieldException("Producer field is mandatory");
        }
        this.producer = producer;
    }

    public void setAnalogDiscPrice(double analogDiscPrice) throws InvalidFieldException {
        if(analogDiscPrice < 0) {
            throw new InvalidFieldException("Invalid value");
        }
        this.analogDiscPrice = analogDiscPrice;
    }

    // *** ADDITIONAL LOGIC

    @Override
    public double finalCost() {
        return this.getBaseCost() +
                this.getBaseCost() * IAudio.getCharge() / 100 +
                this.getBaseCost() * recordingCost / 100 ;
    }

    @Override
    public Map<String, Double> allCosts() {
        Map<String, Double> prices = new HashMap<>();
        prices.put("Audio", finalCost());
        prices.put("Analog Disc", analogDiscPrice);
        return prices;
    }
}
