package mas.service;

import mas.model.EmployeeRole;

public interface IEmployeeService {
    public abstract void addRole(Long id, EmployeeRole role);
    public abstract void deleteRole(Long id, EmployeeRole role);
    public abstract Double calculateSalary(Long id, boolean withBenefit);
}
