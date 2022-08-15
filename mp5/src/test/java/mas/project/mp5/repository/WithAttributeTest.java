package mas.project.mp5.repository;

import mas.project.mp5.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class WithAttributeTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderedBookRepository orderedBookRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Book book;
    BOrder order;
    OrderedBook orderedBook;
    Customer customer;

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

        book = Book.builder()
                .ISBN("1234567890123")
                .name("Book" + LocalDateTime.now())
                .publishDate(Date.valueOf("2002-04-13"))
                .cost(new Cost(10., true))
                .genres(Set.of("Comedy"))
                .build();

        order = BOrder.builder()
                .creationDate(Date.valueOf("2002-04-13"))
                .address(new Address("Poland", "Warsaw", "Koszykowa", "2a", 1))
                .isDelivered(false)
                .customer(customer)
                .build();

        orderedBook = OrderedBook.builder()
                .book(book)
                .order(order)
                .quantity(10)
                .build();
    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(bookRepository);
        assertNotNull(orderRepository);
        assertNotNull(orderedBookRepository);
        assertNotNull(customerRepository);
    }

    @Test
    public void testSave() {
        customer.getOrders().add(order);
        customerRepository.save(customer);

        book.getOrderedBooks().add(orderedBook);
        bookRepository.save(book);

        order.getOrderedBooks().add(orderedBook);
        orderRepository.save(order);

        orderedBookRepository.save(orderedBook);
        entityManager.flush();

        Optional<Book> bookWithId = bookRepository.findByIdWithBookOrders(book.getId());
        Optional<BOrder> orderWithId = orderRepository.findByIdWithBookOrders(order.getId());
        assertTrue(bookWithId.isPresent());
        assertTrue(orderWithId.isPresent());
        assertEquals(1, bookWithId.get().getOrderedBooks().size());
        assertEquals(1, orderWithId.get().getOrderedBooks().size());
    }
}
