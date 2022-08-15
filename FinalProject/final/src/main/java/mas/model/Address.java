package mas.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Address {
    @NotBlank(message = "Country is mandatory")
    private String country;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Street is mandatory")
    private String street;

    @NotBlank(message = "House Number is mandatory")
    private String houseNumber;

    @Nullable
    private Integer apartment;
}
