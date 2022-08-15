package attribute;

import general.Address;
import util.InvalidFieldException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Order implements Serializable {
    private static List<Order> orders = new ArrayList<>();

    private int orderNumber;
    private LocalDateTime creationDate;
    private Address address;
    private boolean isDelivered;
    private Set<OrderedBook> orderedBooks = new HashSet<>();

    public Order(int orderNumber, Address address) throws InvalidFieldException {
        setOrderNumber(orderNumber);
        setCreationDate(LocalDateTime.now());
        setAddress(address);
        setDelivered(false);

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

    public Set<OrderedBook> getOrderedBooks() {
        return Collections.unmodifiableSet(orderedBooks);
    }

    // *** SETTERS

    public void setOrderNumber(int orderNumber) throws InvalidFieldException {
        if(orderNumber < 0){
            throw new InvalidFieldException("attribute.Order number field is mandatory");
        }
        this.orderNumber = orderNumber;
    }

    public void setCreationDate(LocalDateTime creationDate) throws InvalidFieldException {
        if(creationDate == null || creationDate.isAfter(LocalDateTime.now())){
            throw new InvalidFieldException("Creation date is mandatory");
        }
        this.creationDate = creationDate;
    }

    public void setAddress(Address address) throws InvalidFieldException {
        if(address == null){
            throw new InvalidFieldException("general.Address field is mandatory");
        }
        this.address = address;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    // *** ADDITIONAL LOGIC

    public void addOrderedBook(OrderedBook orderedBook) throws InvalidFieldException {
        if(orderedBook != null && !orderedBooks.contains(orderedBook)){
            orderedBooks.add(orderedBook);
            orderedBook.setOrder(this);
        }
    }

    public void removeOrderedBook(OrderedBook orderedBook) throws InvalidFieldException {
        if(orderedBook != null && orderedBooks.contains(orderedBook)) {
            orderedBooks.remove(orderedBook);
            orderedBook.setOrder(null);
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
        return "attribute.Order{" +
                "orderNumber=" + orderNumber +
                ", creationDate=" + creationDate +
                ", address=" + address +
                ", isDelivered=" + isDelivered +
                '}';
    }
}
