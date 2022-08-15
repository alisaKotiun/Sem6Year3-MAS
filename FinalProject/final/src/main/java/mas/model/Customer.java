package mas.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED) //all the classes in different tables
@SuperBuilder
public abstract class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @NotNull(message = "Address is mandatory")
    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    @NotNull
    private LoyaltyStatus status;

    @NotNull(message = "Discount is mandatory")
    @Min(0)
    private Integer discount;

    private String description;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Order> orders = new HashSet<>();
}
