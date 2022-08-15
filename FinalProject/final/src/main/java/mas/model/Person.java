package mas.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED) //all the classes in different tables
@SuperBuilder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @NotNull(message = "Date of Birth is mandatory")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past(message = "Date of Birth cannot be from future")
    private Date dateOfBirth;


    @OneToOne(mappedBy = "person", cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customer_individual_id", referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Individual customerIndividual;
}
