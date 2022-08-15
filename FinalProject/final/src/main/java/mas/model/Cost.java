package mas.model;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
@Access(AccessType.PROPERTY)
@ToString
public class Cost {

    public Cost() {}

    public Cost(Double baseValue, Integer discount) {
        this.baseValue = baseValue;
        this.discount = discount;
    }

    @NotNull(message = "Base Value is mandatory")
    @Min(0)
    private Double baseValue;

    @NotNull(message = "Discount is mandatory")
    @Min(0)
    private Integer discount;

    @Setter(AccessLevel.NONE)
    private Integer maxDiscount;

    public Double getBaseValue() {
        return baseValue;
    }

    public Integer getDiscount() {
        return discount;
    }

    @Transient
    public Double getFinalCost() {
        return (this.baseValue - this.baseValue * this.discount / 100);
    }

    @Transient
    @Query(nativeQuery = true, value = "SELECT s.discount FROM Cost_util s LIMIT 1")
    public Integer getMaxDiscount() {
        return maxDiscount;
    }

    public void setBaseValue(Double baseValue) {
        this.baseValue = baseValue;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

}
