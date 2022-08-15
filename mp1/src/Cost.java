import java.io.Serializable;

public class Cost implements Serializable {
    private double baseValue;
    private boolean withDiscount;

    public Cost(double baseValue, boolean withDiscount) {
        this.baseValue = baseValue;
        this.withDiscount = withDiscount;
    }

    // *** GETTERS

    public double getBaseValue() {
        return baseValue;
    }

    public boolean isWithDiscount() {
        return withDiscount;
    }

    // *** SETTERS

    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }

    public void setWithDiscount(boolean withDiscount) {
        this.withDiscount = withDiscount;
    }

    @Override
    public String toString() {
        return "Cost {" +
                "value = " + baseValue + "$" +
                (withDiscount ? " with discount" : " without discount") +
                '}';
    }
}
