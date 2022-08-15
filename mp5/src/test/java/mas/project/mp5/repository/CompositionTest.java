package mas.project.mp5.repository;

import mas.project.mp5.model.Address;
import mas.project.mp5.model.BOrder;
import mas.project.mp5.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CompositionTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Customer customer;
    BOrder order;

    @BeforeEach
    public void initData() {
        customer = Customer.builder()
                .firstName("FN2")
                .lastName("LN2")
                .dateOfBirth(Date.valueOf("2002-04-13"))
                .email("example2@gmail.com")
                .phoneNumber("223-433-233")
                .isLoyal(false)
                .build();

    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(customerRepository);
        assertNotNull(orderRepository);
    }

    @Test
    public void testSave() {
        customer.getOrders().add(order);
        customerRepository.save(customer);

        order = BOrder.builder()
                .creationDate(Date.valueOf("2002-04-13"))
                .address(new Address("Poland", "Warsaw", "Koszykowa", "2a", 1))
                .customer(customer)
                .isDelivered(false)
                .build();

        orderRepository.save(order);
        entityManager.flush();

        Optional<Customer> byId = customerRepository.findByIdWithOrders(customer.getId());
        assertTrue(byId.isPresent());
        assertEquals(1, byId.get().getOrders().size());
        assertEquals(customer.getLastName(), order.getCustomer().getLastName());
    }
}
