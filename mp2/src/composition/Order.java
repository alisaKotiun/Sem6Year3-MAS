package composition;

import general.Address;
import util.InvalidFieldException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Order implements Serializable {
    private static List<Order> orders = new ArrayList<>();
    private static int counter = 1;

    private int orderNumber;
    private LocalDateTime creationDate;
    private Address address;
    private boolean isDelivered;
    private Customer customer;

    private Order(Address address, Customer customer) throws InvalidFieldException {
        setOrderNumber();
        setCreationDate(LocalDateTime.now());
        setAddress(address);
        setDelivered(false);
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

    public boolean isDelivered() {
        return isDelivered;
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

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
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
                '}';
    }
}
