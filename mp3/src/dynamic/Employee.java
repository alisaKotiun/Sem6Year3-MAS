package dynamic;

import util.InvalidFieldException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Employee {
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
    private Set<Order> orders = new HashSet<>();

    public Employee(String firstName, String lastName, LocalDateTime dateOfBirth) throws InvalidFieldException {
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
    }

    // *** GETTERS

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    // *** SETTERS

    public void setFirstName(String firstName) throws InvalidFieldException {
        if(firstName == null || firstName.isEmpty()){
            throw new InvalidFieldException("First name field is mandatory");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws InvalidFieldException {
        if(lastName == null || lastName.isEmpty()){
            throw new InvalidFieldException("Last name field is mandatory");
        }
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) throws InvalidFieldException {
        if(dateOfBirth == null || dateOfBirth.isAfter(LocalDateTime.now())){
            throw new InvalidFieldException("Date of birth is mandatory");
        }
        this.dateOfBirth = dateOfBirth;
    }

    // *** ADDITIONAL LOGIC

    protected void addOrder(Order order) throws InvalidFieldException {
        if(order != null && !orders.contains(order)) {
            orders.add(order);
            order.setEmployee(this);
        }
    }

    protected void removeOrder(Order order) throws InvalidFieldException {
        if(order != null && orders.contains(order)) {
            orders.remove(order);
            order.removeEmployee();
        }
    }
}
