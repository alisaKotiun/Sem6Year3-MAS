package composition;

import general.Individual;
import util.InvalidFieldException;
import util.Util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Customer extends Individual implements Serializable {
    private static List<Customer> customers = new ArrayList<>();

    private String email;
    private String phoneNumber;
    private Set<Order> orders = new HashSet<>();

    public Customer(String firstName, String middleName, String lastName, LocalDateTime dateOfBirth, String email, String phoneNumber) throws InvalidFieldException {
        super(firstName, middleName, lastName, dateOfBirth);
        setEmail(email);
        setPhoneNumber(phoneNumber);

        customers.add(this);
    }

    public Customer(String firstName, String middleName, String lastName, LocalDateTime dateOfBirth, String phoneNumber) throws InvalidFieldException {
        this(firstName, middleName, lastName, dateOfBirth, null, phoneNumber);
    }

    public Customer(String firstName, String lastName, LocalDateTime dateOfBirth, String email, String phoneNumber) throws InvalidFieldException {
        this(firstName, null, lastName, dateOfBirth, email, phoneNumber);
    }

    public Customer(String firstName, String lastName, LocalDateTime dateOfBirth, String phoneNumber) throws InvalidFieldException {
        this(firstName, null, lastName, dateOfBirth, null, phoneNumber);
    }

    // *** GETTERS

    public static List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Set<Order> getOrders() {
        return Collections.unmodifiableSet(orders);
    }

    // *** SETTERS

    public void setEmail(String email) throws InvalidFieldException {
        if(email != null && !email.isEmpty() && !Util.validateEmail(email)){
            throw new InvalidFieldException("Email format is incorrect");
        }
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) throws InvalidFieldException {
        if(phoneNumber == null || phoneNumber.isEmpty()){
            throw new InvalidFieldException("Phone field is mandatory");
        }
        this.phoneNumber = phoneNumber;
    }

    // *** ADDITIONAL LOGIC

    protected void addOrder(Order order) {
        if(order != null && !orders.contains(order)) {
            orders.add(order);
        }
    }

    public void removeOrder(Order order) {
        if(order != null && orders.contains(order)) {
            orders.remove(order);
            Order.removeOrder(order);
        }
    }

    public void removeAllOrders() {
        List<Order> clones = new ArrayList<>(orders);

        for(Order order : clones) {
            removeOrder(order);
        }
    }

    public static void removeCustomer(Customer customer) {
        if(customer != null && customers.contains(customer)) {
            customer.removeAllOrders();
            customers.remove(customer);
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orders=" + orders.toString() +
                '}';
    }
}
