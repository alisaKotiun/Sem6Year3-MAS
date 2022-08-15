package mas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class Company extends Customer{
    @NotBlank(message = "Name is mandatory")
    private String name;

    //@Column(unique = true)
    @NotBlank(message = "REGON is mandatory")
    private String REGON;
}
