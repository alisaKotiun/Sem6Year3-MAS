package mas.project.mp5.model;


import lombok.AccessLevel;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Embeddable
@Access(AccessType.PROPERTY)
@ToString
public class Cost {

    private static int discount = 10;

    public Cost() {

    }

    public Cost(double baseValue, boolean withDiscount) {
        this.baseValue = baseValue;
        this.withDiscount = withDiscount;
    }

    @NotNull(message = "Base Value is mandatory")
    @Min(0)
    private Double baseValue;

    @NotNull(message = "With Discount is mandatory")
    private boolean withDiscount;

    @Setter(AccessLevel.NONE)
    private Double finalCost;

    public Double getBaseValue() {
        return baseValue;
    }

    public boolean isWithDiscount() {
        return withDiscount;
    }

    public Double getFinalCost() {
        return withDiscount ? (baseValue - baseValue * discount / 100) : baseValue;
    }

    public void setBaseValue(Double baseValue) {
        this.baseValue = baseValue;
    }

    public void setWithDiscount(boolean withDiscount) {
        this.withDiscount = withDiscount;
    }

    private void setFinalCost(Double finalCost) {
        this.finalCost = finalCost;
    }
}
