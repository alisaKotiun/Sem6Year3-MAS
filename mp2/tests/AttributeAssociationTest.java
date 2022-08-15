import attribute.Book;
import attribute.Order;
import attribute.OrderedBook;
import general.Address;
import general.Cost;
import org.junit.Before;
import org.junit.Test;
import util.InvalidFieldException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class AttributeAssociationTest {
    Address address1, address2;
    Order order1, order2;
    Book book1, book2;
    Set<String> genres = new HashSet<>();

    @Before
    public void init() throws InvalidFieldException {
        address1 = new Address("Poland", "Warsaw", "Koszykowa", "86a");
        address2 = new Address("Poland", "Warsaw", "Koszykowa", "86b");

        order1 = new Order(1, address1);
        order2 = new Order(2, address2);

        genres.add("Comedy");
    }

    @Test
    public void createOrderedBook() throws InvalidFieldException {
        book1 = new Book("9876543210121", "Book1", LocalDateTime.now(), genres, new Cost(5, false));
        book2 = new Book("9876543210322", "Book2", LocalDateTime.now(), genres, new Cost(5, false));

        OrderedBook orderedBook = new OrderedBook(order1, book1, 10);
        assertFalse(OrderedBook.isUnique(orderedBook));

    }

    @Test
    public void setOrder() throws InvalidFieldException {
        book1 = new Book("9876543210123", "Book1", LocalDateTime.now(), genres, new Cost(5, false));
        book2 = new Book("9876543210324", "Book2", LocalDateTime.now(), genres, new Cost(5, false));

        OrderedBook orderedBook = new OrderedBook(order1, book1, 10);
        assertFalse(OrderedBook.isUnique(orderedBook));
        assertEquals(order1, orderedBook.getOrder());

        orderedBook.setOrder(order2);
        assertEquals(order2, orderedBook.getOrder());

        orderedBook.setOrder(null);
        assertNull(orderedBook.getOrder());
        assertNull(orderedBook.getBook());
    }

    @Test
    public void setBook() throws InvalidFieldException {
        book1 = new Book("9876543210125", "Book1", LocalDateTime.now(), genres, new Cost(5, false));
        book2 = new Book("9876543210326", "Book2", LocalDateTime.now(), genres, new Cost(5, false));

        OrderedBook orderedBook = new OrderedBook(order1, book1, 10);
        assertFalse(OrderedBook.isUnique(orderedBook));
        assertEquals(book1, orderedBook.getBook());

        orderedBook.setBook(book2);
        assertEquals(book2, orderedBook.getBook());

        orderedBook.setBook(null);
        assertNull(orderedBook.getOrder());
        assertNull(orderedBook.getBook());
        assertEquals(0, order1.getOrderedBooks().size());
        assertEquals(0, book1.getOrderedBooks().size());
    }

    @Test
    public void remove() throws InvalidFieldException {
        book1 = new Book("9876543210127", "Book1", LocalDateTime.now(), genres, new Cost(5, false));
        book2 = new Book("9876543210328", "Book2", LocalDateTime.now(), genres, new Cost(5, false));

        OrderedBook orderedBook = new OrderedBook(order1, book1, 10);
        assertFalse(OrderedBook.isUnique(orderedBook));

        orderedBook.remove();
        assertNull(orderedBook.getOrder());
        assertNull(orderedBook.getBook());
    }

    @Test
    public void addOrderedBook() throws InvalidFieldException {
        book1 = new Book("9876543210129", "Book1", LocalDateTime.now(), genres, new Cost(5, false));
        book2 = new Book("9876543210320", "Book2", LocalDateTime.now(), genres, new Cost(5, false));

        OrderedBook orderedBook = new OrderedBook(order1, book1, 10);
        order2.addOrderedBook(orderedBook);
        assertEquals(0, order1.getOrderedBooks().size());
        assertEquals(1, order2.getOrderedBooks().size());
        assertEquals(order2, orderedBook.getOrder());

        book2.addOrderedBook(orderedBook);
        assertEquals(0, book1.getOrderedBooks().size());
        assertEquals(1, book2.getOrderedBooks().size());
        assertEquals(book2, orderedBook.getBook());
    }

    @Test
    public void addExisting() throws InvalidFieldException {
        book1 = new Book("9876543210111", "Book1", LocalDateTime.now(), genres, new Cost(5, false));
        book2 = new Book("9876543210312", "Book2", LocalDateTime.now(), genres, new Cost(5, false));

        OrderedBook orderedBook = new OrderedBook(order1, book1, 10);
        orderedBook.setOrder(order2);
        assertEquals(0, order1.getOrderedBooks().size());
        assertEquals(1, order2.getOrderedBooks().size());
        assertEquals(order2, orderedBook.getOrder());
    }
}
