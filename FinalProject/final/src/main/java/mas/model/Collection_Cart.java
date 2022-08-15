package mas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"collection_id", "shopping_cart_id"})
})
public class Collection_Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Min(1)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    @NotNull
    @JsonBackReference
    private Collection collection;


    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    @NotNull
    @JsonBackReference
    private ShoppingCart shoppingCart;
}
