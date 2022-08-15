package mas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@SubsetConstraint
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CollectionStatus status;


    @OneToMany(mappedBy = "collection", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    @Getter(AccessLevel.PACKAGE)
    private Set<Book> books = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "cover_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private Book cover;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @JsonManagedReference
    private Set<Collection_Cart> collectionCarts = new HashSet<>();

    @Transient
    public Double getFinalPrice() {
        Double result = 0.;

        for(Book book : books) {
            result += book.getCost().getFinalCost();
        }
        return result;
    }

    public Integer getItemsNumber() {
        return books.size();
    }

}
