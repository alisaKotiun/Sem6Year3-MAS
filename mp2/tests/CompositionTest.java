import composition.Customer;
import composition.Order;
import general.Address;
import org.junit.Before;
import org.junit.Test;
import util.InvalidFieldException;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CompositionTest {
    Customer customer1, customer2;
    Address address;

    @Before
    public void init() throws InvalidFieldException {
        LocalDateTime dateTime = LocalDateTime.now();
        address = new Address("Poland", "Warsaw", "Koszykowa", "86a");

        customer1 = new Customer("FN1", "LN1", dateTime, "12344321");
        customer2 = new Customer("FN2", "LN2", dateTime, "12344321");
    }

    @Test
    public void create() throws InvalidFieldException {
        Order order1 = Order.createOrder(address, customer1);
        Order order2 = Order.createOrder(address, customer2);

        assertEquals("FN1", order1.getCustomer().getFirstName());
        assertEquals("FN2", order2.getCustomer().getFirstName());
        assertEquals(1, customer1.getOrders().size());
        assertEquals(1, customer2.getOrders().size());

        Order.removeOrder(order1);
        Order.removeOrder(order2);
    }

    @Test
    public void removeOrder() throws InvalidFieldException {
        Order order1 = Order.createOrder(address, customer1);
        Order order2 = Order.createOrder(address, customer2);
        assertEquals("FN1", order1.getCustomer().getFirstName());
        assertEquals("FN2", order2.getCustomer().getFirstName());

        Order.removeOrder(order1);
        assertEquals(0, customer1.getOrders().size());
        assertNull(order1.getCustomer());
        Order.removeOrder(order2);
    }

    @Test
    public void removeAllOrders() throws InvalidFieldException {
        Order order1 = Order.createOrder(address, customer1);
        Order order2 = Order.createOrder(address, customer1);
        assertEquals("FN1", order1.getCustomer().getFirstName());
        assertEquals("FN1", order2.getCustomer().getFirstName());

        int size = Customer.getCustomers().size();
        Customer.removeCustomer(customer1);
        customer2.removeAllOrders();

        assertEquals(0, customer2.getOrders().size());
        assertEquals(0, Order.getOrders().size());
        assertEquals(size-1, Customer.getCustomers().size());

        assertNull(order1.getCustomer());
        assertNull(order2.getCustomer());

        order1 = Order.createOrder(address, customer1);
        order2 = Order.createOrder(address, customer1);
        assertEquals("FN1", order1.getCustomer().getFirstName());
        assertEquals("FN1", order2.getCustomer().getFirstName());

        Order.removeAllOrders();
        assertEquals(0, Order.getOrders().size());
        assertEquals(0, customer1.getOrders().size());
    }
}
