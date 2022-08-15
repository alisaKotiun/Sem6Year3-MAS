package mas.project.mp5;

import lombok.RequiredArgsConstructor;
import mas.project.mp5.model.*;
import mas.project.mp5.repository.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer {


    private final PublishingHouseRepository publishingHouseRepository;
    private final AuthorRepository authorRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final OrderedBookRepository orderedBookRepository;

    @EventListener
    public void atStart(ContextRefreshedEvent event) {
        System.out.println("EVENT: " + event.toString());

        List<Customer> customers = customerFactory(1);
        List<BOrder> orders = orderFactory(1, customers.get(0));
        List<Book> books = bookFactory(2);

        publishingHouseRepository.saveAll(publishingHouseFactory(1));
        authorRepository.saveAll(authorFactory(1));
        customerRepository.saveAll(customers);
        bookRepository.saveAll(books);
        orderRepository.saveAll(orderFactory(1, customers.get(0)));

        createBasicAssociation();
        createWithAttribute(books.get(0), orders.get(0));

        System.out.println(publishingHouseRepository.findAll());
        System.out.println(authorRepository.findAll());
        System.out.println(customerRepository.findAll());
        System.out.println(bookRepository.findAll());
    }

    private List<PublishingHouse> publishingHouseFactory(int n) {
        List<PublishingHouse> result = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            result.add(PublishingHouse.builder()
                    .name("BookHouse" + i)
                    .email(i + "example@gmail.com")
                    .build());
        }
        return result;
    }

    private List<Author> authorFactory(int n) {
        List<Author> result = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            result.add(Author.builder()
                    .firstName("AuthorFN" + i)
                    .lastName("AuthorLN" + i)
                    .dateOfBirth(Date.valueOf("2002-04-13"))
                    .email(i + "exampleAuthor@gmail.com")
                    .build());
        }
        return result;
    }

    private List<Customer> customerFactory(int n) {
        List<Customer> result = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            result.add(Customer.builder()
                    .firstName("CustomerFN" + i)
                    .lastName("CustomerLN" + i)
                    .dateOfBirth(Date.valueOf("2002-04-13"))
                    .email(i + "exampleCustomer@gmail.com")
                    .phoneNumber("223-433-233")
                    .isLoyal(false)
                    .build());
        }
        return result;
    }

    private List<Book> bookFactory (int n) {
        List<Book> result = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            result.add(Book.builder()
                    .ISBN("1234567890123")
                    .name("Book" + LocalDateTime.now())
                    .publishDate(Date.valueOf("2002-04-13"))
                    .cost(new Cost(10. + i, true))
                    .genres(Set.of("Comedy" + i))
                    .build());
        }
        return result;
    }

    private List<BOrder> orderFactory(int n, Customer customer) {
        List<BOrder> result = new ArrayList<>();

        for( int i = 0; i < n; i++) {
            BOrder or = BOrder.builder()
                    .creationDate(Date.valueOf("2002-04-13"))
                    .address(new Address("Poland", "Warsaw", "Koszykowa", "2a", 1))
                    .customer(customer)
                    .isDelivered(false)
                    .build();
            result.add(or);
            customer.getOrders().add(or);
        }
        return result;
    }

    private void createBasicAssociation() {
        PublishingHouse ph = publishingHouseFactory(1).get(0);
        Author a = authorFactory(1).get(0);

        ph.getAuthors().add(a);
        publishingHouseRepository.save(ph);

        a.setSignContractWith(ph);
        authorRepository.save(a);
    }

    private void createWithAttribute(Book book, BOrder order) {
        OrderedBook orderedBook = OrderedBook.builder()
                .book(book)
                .order(order)
                .quantity(10)
                .build();

        book.getOrderedBooks().add(orderedBook);
        bookRepository.save(book);

        order.getOrderedBooks().add(orderedBook);
        orderRepository.save(order);

        orderedBookRepository.save(orderedBook);
    }
}
