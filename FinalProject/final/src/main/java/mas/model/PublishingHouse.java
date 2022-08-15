package mas.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PublishingHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name field is mandatory")
    @Size(min = 2, max = 255)
    private String name;

    @NotBlank(message = "Email field is mandatory")
    @Email
    private String email;

    @NotNull(message = "Address is mandatory")
    @Embedded
    private Address address;


    @OneToMany(mappedBy = "publishingHouse", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Contract> contracts = new HashSet<>();
}
