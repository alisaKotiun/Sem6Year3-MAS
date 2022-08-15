package ordered;

import helper.Address;
import helper.InvalidFieldException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Order implements Comparable<Order> {
    private static List<Order> orders = new ArrayList<>();
    private static int counter = 1;

    private int orderNumber;
    private LocalDateTime creationDate;
    private Address address;
    private Customer customer;

    private Order(Address address, Customer customer) throws InvalidFieldException {
        setOrderNumber();
        setCreationDate(LocalDateTime.now());
        setAddress(address);
        setCustomer(customer);

        orders.add(this);
    }

    // *** GETTERS

    public static List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getAddress() {
        return address.toString();
    }

    public Customer getCustomer() {
        return customer;
    }

    // *** SETTERS

    private void setOrderNumber()  {
        this.orderNumber = counter;
        counter += 1;
    }

    private void setCreationDate(LocalDateTime creationDate) throws InvalidFieldException {
        if(creationDate == null || creationDate.isAfter(LocalDateTime.now())){
            throw new InvalidFieldException("Creation date is mandatory");
        }
        this.creationDate = creationDate;
    }

    public void setAddress(Address address) throws InvalidFieldException {
        if(address == null){
            throw new InvalidFieldException("Address field is mandatory");
        }
        this.address = address;
    }

    private void setCustomer(Customer customer) throws InvalidFieldException {
        if(customer == null) {
            throw new InvalidFieldException("Can't create an order without the customer");
        }

        this.customer = customer;
    }

    // *** ADDITIONAL LOGIC

    public static Order createOrder(Address address, Customer customer) throws InvalidFieldException {
        Order order = new Order(address, customer);
        customer.addOrder(order);
        return order;
    }

    public static void removeOrder(Order order) {
        if(order != null && orders.contains(order)) {
            Customer tmp = order.customer;

            orders.remove(order);
            order.customer = null;
            tmp.removeOrder(order);
        }
    }

    public static void removeAllOrders() {
        List<Order> clones = new ArrayList<>(orders);

        for(Order order : clones) {
            removeOrder(order);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderNumber == order.orderNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public int compareTo(Order o) {
        int result = this.creationDate.compareTo(o.getCreationDate());
        if(result == 0) {
            result = Integer.compare(o.getOrderNumber(), this.orderNumber);
        }
        return result;
    }
}
