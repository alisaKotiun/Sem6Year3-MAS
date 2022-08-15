package mas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Creation Date is mandatory")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past(message = "Creation Date cannot be from future")
    private Date creationDate;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @JsonManagedReference
    private Set<Collection_Cart> collectionCarts = new HashSet<>();

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @JsonManagedReference
    private Set<Book_Cart> bookCarts = new HashSet<>();
}
