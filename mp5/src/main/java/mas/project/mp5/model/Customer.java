package mas.project.mp5.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class Customer extends Individual {

    @NotBlank(message = "Email field is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Phone Number is mandatory")
    private String phoneNumber;

    @NonNull
    private Boolean isLoyal;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<BOrder> orders = new HashSet<>();
}
