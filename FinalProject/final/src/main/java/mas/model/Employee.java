package mas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class Employee extends Person{
    @NotBlank(message = "Login is mandatory")
    private String login;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotNull(message = "Salary is mandatory")
    @Min(0)
    private Double salary;

    @ElementCollection(targetClass = EmployeeRole.class)
    @JoinTable(name = "employeeRole", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "role" , nullable = false)
    @Enumerated(EnumType.STRING)
    @Size(min=1)
    private Set<EmployeeRole> roles = new HashSet<>();

}
