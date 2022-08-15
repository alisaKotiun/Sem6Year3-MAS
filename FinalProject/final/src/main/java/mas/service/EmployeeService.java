package mas.service;

import lombok.RequiredArgsConstructor;
import mas.model.Employee;
import mas.model.EmployeeRole;
import mas.repository.EmployeeBenefitRepository;
import mas.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository repository;
    private final EmployeeBenefitRepository benefitRepository;

    @Override
    public void addRole(Long id, EmployeeRole role) {
        Iterable<Employee> employees = repository.findAllById(getIterableFromIterator(Set.of(id).iterator()));
        for(Employee employee : employees) {
            employee.getRoles().add(role);
        }
        repository.saveAll(employees);
    }

    @Override
    public void deleteRole(Long id, EmployeeRole role) {
        Iterable<Employee> employees = repository.findAllById(getIterableFromIterator(Set.of(id).iterator()));
        for(Employee employee : employees) {
            if(employee.getRoles().size() == 1 && employee.getRoles().contains(role)) {
                throw new IllegalArgumentException("Role can't be deleted for " + id);
            }
            employee.getRoles().remove(role);
        }
        repository.saveAll(employees);
    }

    @Override
    public Double calculateSalary(Long id, boolean withBenefit) {
        Optional<Employee> emp = repository.findById(id);
        Double benefit = 0.;
        if(emp.isPresent()){
            if(emp.get().getRoles().contains(EmployeeRole.COURIER)) {
                benefit = withBenefit ? benefitRepository.findFirstByRole(EmployeeRole.COURIER).getBenefit() : 0;
            }
            if(emp.get().getRoles().contains(EmployeeRole.CONSULTANT)) {
                benefit = withBenefit ? benefitRepository.findFirstByRole(EmployeeRole.CONSULTANT).getBenefit() : 0;
            }
            if(emp.get().getRoles().contains(EmployeeRole.MANAGER)) {
                benefit = withBenefit ? benefitRepository.findFirstByRole(EmployeeRole.MANAGER).getBenefit() : 0;
            }
            return emp.get().getSalary() + benefit;
        }

        return 0.;
    }

    private static <T> Iterable<T> getIterableFromIterator(Iterator<T> iterator) {
        return () -> iterator;
    }
}
