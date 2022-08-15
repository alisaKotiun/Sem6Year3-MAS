package mas.project.mp5.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data //from lombok
@NoArgsConstructor
@AllArgsConstructor
@Builder //for object creation
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

    @OneToMany(mappedBy = "signContractWith", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Author> authors = new HashSet<>();
}
