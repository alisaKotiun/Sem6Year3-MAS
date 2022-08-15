package mas.project.mp5.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class Author extends Individual{

    @NotBlank(message = "Email field is mandatory")
    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name = "publishing_house_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PublishingHouse signContractWith;
}
