package mas.repository;

import mas.model.EmployeeBenefit;
import mas.model.EmployeeRole;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeBenefitRepository extends CrudRepository<EmployeeBenefit, Long> {
    public EmployeeBenefit findFirstByRole(EmployeeRole role);
}
