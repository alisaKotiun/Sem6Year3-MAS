import helper.Address;
import helper.InvalidFieldException;
import ordered.Customer;
import ordered.Order;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class Ordered {
    Customer customer1, customer2;
    Address address;

    @Before
    public void init() throws InvalidFieldException {
        LocalDateTime dateTime = LocalDateTime.now();
        address = new Address("Poland", "Warsaw", "Koszykowa", "86a");

        customer1 = new Customer("FN1", "LN1", dateTime, "alisa@gmail.com", "12344321");
        customer2 = new Customer("FN2", "LN2", dateTime, "alisa@gmail.com", "12344321");
    }

    @Test
    public void create() throws InvalidFieldException, InterruptedException {
        Order order1 = Order.createOrder(address, customer1);
        Thread.sleep(2000);
        Order order3 = Order.createOrder(address, customer1);
        Order order4 = Order.createOrder(address, customer1);

        for(Order o : customer1.getOrders()) {
            System.out.println(o);
        }
    }
}
