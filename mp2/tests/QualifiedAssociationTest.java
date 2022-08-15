import general.Cost;
import org.junit.Before;
import org.junit.Test;
import qualified.Book;
import qualified.Collection;
import qualified.CollectionStatus;
import util.InvalidFieldException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class QualifiedAssociationTest {
    Book book1, book2;
    Collection collection1, collection2;
    Set<String> genres = new HashSet<>();

    @Before
    public void init() throws InvalidFieldException {
        collection1 = new Collection("Collection1", CollectionStatus.PLANNED);
        collection2 = new Collection("Collection2", CollectionStatus.ACTIVE);

        genres.add("Comedy");
    }

    @Test
    public void addBook() throws InvalidFieldException {
        book1 = new Book("0876543210121", "Book1", LocalDateTime.now(), genres, new Cost(5, false));
        book2 = new Book("1876543210321", "Book2", LocalDateTime.now(), genres, new Cost(5, false));

        collection1.addBook(book1);
        assertEquals(1, collection1.getBooks().size());
        assertEquals("Collection1", book1.getCollection().getName());

        collection1.addBook(book2);
        assertEquals(2, collection1.getBooks().size());

        collection2.addBook(book1);
        assertEquals(1, collection1.getBooks().size());
        assertEquals(1, collection2.getBooks().size());
        assertEquals("Collection2", book1.getCollection().getName());
        assertEquals("Collection1", book2.getCollection().getName());

        collection1.removeBook(book2);
        collection2.removeBook(book1);
    }

    @Test
    public void removeBook() throws InvalidFieldException {
        book1 = new Book("2876543210121", "Book1", LocalDateTime.now(), genres, new Cost(5, false));
        book2 = new Book("3876543210321", "Book2", LocalDateTime.now(), genres, new Cost(5, false));

        collection1.addBook(book1);
        collection2.addBook(book2);
        assertEquals(1, collection1.getBooks().size());
        assertEquals(1, collection2.getBooks().size());

        collection1.removeBook(book1);
        collection2.removeBook(book1);
        assertEquals(0, collection1.getBooks().size());
        assertEquals(1, collection2.getBooks().size());

        collection2.removeBook(book2);
    }

    @Test
    public void setCollection() throws InvalidFieldException {
        book1 = new Book("4876543210121", "Book1", LocalDateTime.now(), genres, new Cost(5, false));
        book2 = new Book("5876543210321", "Book2", LocalDateTime.now(), genres, new Cost(5, false));

        book1.setCollection(collection1);
        book2.setCollection(collection1);
        assertEquals(2, collection1.getBooks().size());
        assertEquals("Collection1", book1.getCollection().getName());
        assertEquals("Collection1", book2.getCollection().getName());

        book1.setCollection(collection2);
        assertEquals(1, collection1.getBooks().size());
        assertEquals(1, collection2.getBooks().size());
        assertEquals("Collection2", book1.getCollection().getName());
        assertEquals("Collection1", book2.getCollection().getName());

        book1.removeCollection();
        book2.removeCollection();
    }

    @Test
    public void setISBN() throws InvalidFieldException {
        book1 = new Book("6876543210121", "Book1", LocalDateTime.now(), genres, new Cost(5, false));
        book2 = new Book("7876543210321", "Book2", LocalDateTime.now(), genres, new Cost(5, false));

        book1.setCollection(collection1);
        book2.setCollection(collection1);
        assertEquals(2, collection1.getBooks().size());
        assertEquals("Book1", collection1.getBookByISBN(book1.getISBN()).getName());
        assertEquals("Book2", collection1.getBookByISBN(book2.getISBN()).getName());
        assertNull(collection2.getBookByISBN(book1.getISBN()));

        book1.setISBN("8876543210321");

        assertEquals(2, collection1.getBooks().size());
        assertEquals("Book1", collection1.getBookByISBN(book1.getISBN()).getName());
        assertEquals("Book2", collection1.getBookByISBN(book2.getISBN()).getName());
        assertNull(collection1.getBookByISBN("6876543210121"));

        book1.removeCollection();
        book2.removeCollection();
    }
}
