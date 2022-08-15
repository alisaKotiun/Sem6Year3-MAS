package ordered;

import helper.Individual;
import helper.InvalidFieldException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer extends Individual {
    private static List<Customer> customers = new ArrayList<>();

    private String email;
    private String phoneNumber;
    private List<Order> orders = new ArrayList<>();

    public Customer(String firstName, String lastName, LocalDateTime dateOfBirth, String email, String phoneNumber) throws InvalidFieldException {
        super(firstName, lastName, dateOfBirth);
        setEmail(email);
        setPhoneNumber(phoneNumber);

        customers.add(this);
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

    public List<Order> getOrders() {
        //Collections.sort(orders);
        return Collections.unmodifiableList(orders);
    }

    // *** SETTERS

    public void setEmail(String email) throws InvalidFieldException {
        if(email != null && !email.isEmpty() && !validateEmail(email)){
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
        Collections.sort(orders);
    }

    public void removeOrder(Order order) {
        if(order != null && orders.contains(order)) {
            orders.remove(order);
            Order.removeOrder(order);
        }
        Collections.sort(orders);
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

    private static boolean validateEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }
}
