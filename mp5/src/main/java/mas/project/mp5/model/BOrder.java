package mas.project.mp5.model;

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
@Table(name = "B_ORDER")
public class BOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Creation Date is mandatory")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past(message = "Creation Date cannot be from future")
    private Date creationDate;

    @NotNull(message = "Address is mandatory")
    @Embedded
    private Address address;

    @NotNull(message = "Is Delivered is mandatory")
    private boolean isDelivered;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<OrderedBook> orderedBooks = new HashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false, updatable = false)
    private Customer customer;
}
