import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.*;

public class BookTest {
    Cost cost1, cost2;

    @Before
    public void init(){
        cost1 = new Cost(10, true);
        cost2 = new Cost(5, false);
    }

    @Test
    public void createComplexAttribute(){
        assertEquals(10, cost1.getBaseValue(),  0);
        assertTrue(cost1.isWithDiscount());

        assertEquals(5, cost2.getBaseValue(), 0);
        assertFalse(cost2.isWithDiscount());
    }

    @Test
    public void createObjectSuccessfully() throws InvalidFieldException {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime creationDate = LocalDateTime.of(1920, 1, 12, 12, 12);
        Set<String> genres = Set.of("Classic");

        Book book1 = new Book("The Great Gatsby", today, creationDate, genres, cost1);
        Book book2 = new Book("1984", today, genres, cost2);

        assertEquals(today, book1.getPublishDate());
        assertEquals(1, book1.getGenres().size());
        assertEquals(cost1.getBaseValue(), book1.getCost().getBaseValue(), 0);


        assertNull(book2.getCreationDate());
        assertEquals("1984", book2.getName());
        assertFalse(book2.getCost().isWithDiscount());
    }

    @Test
    public void createObjectWithFailure() throws InvalidFieldException {
        LocalDateTime today = LocalDateTime.now();
        Set<String> genres = Set.of("Classic");

        assertThrows(InvalidFieldException.class, () -> { new Book(null, today, genres, cost1); });
        assertThrows(InvalidFieldException.class, () -> { new Book("", today, genres, cost1); });
        assertThrows(InvalidFieldException.class, () -> { new Book("1984", null, genres, cost1); });
        assertThrows(InvalidFieldException.class, () -> { new Book("1984", today, null, cost1); });
        assertThrows(InvalidFieldException.class, () -> { new Book("1984", today, Set.of(""), cost1); });
        assertThrows(InvalidFieldException.class, () -> { new Book("1984", today, genres, null); });

    }

    @Test
    public void changeGenres() throws InvalidFieldException {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime creationDate = LocalDateTime.of(1920, 1, 12, 12, 12);
        Set<String> genres = Set.of("Classic");

        Book book1 = new Book("The Great Gatsby", today, creationDate, genres, cost1);
        Book book2 = new Book("1984", today, genres, cost2);

        book1.addGenre("Horror");
        book1.addGenre(null);
        book1.addGenre("");
        book2.removeGenre(null);
        book2.removeGenre("Classic");

        assertEquals(genres.size() + 1, book1.getGenres().size());
        assertEquals(genres.size(), book2.getGenres().size());
        assertThrows(UnsupportedOperationException.class, () -> { book1.getGenres().remove("Classic"); });
    }

    @Test
    public void getBooksByName() throws InvalidFieldException {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime creationDate = LocalDateTime.of(1920, 1, 12, 12, 12);
        Set<String> genres = Set.of("Classic");

        Book book1 = new Book("The Great Gatsby", today, creationDate, genres, cost1);
        Book book2 = new Book("1984", today, genres, cost2);
        Book book3 = new Book("The great alone", today, genres, cost2);

        assertEquals(1, Book.getBooksByName("1984").size());
        assertEquals(0, Book.getBooksByName(null).size());
        assertEquals(2, Book.getBooksByName("great").size());
    }

    @Test
    public void serializeBooks() throws IOException, InvalidFieldException, ClassNotFoundException {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime creationDate = LocalDateTime.of(1920, 1, 12, 12, 12);
        Set<String> genres = Set.of("Classic");

        Book book1 = new Book("Book1", today, creationDate, genres, cost1);
        Book book2 = new Book("Book2", today, genres, cost2);
        Book book3 = new Book("Book3", today, genres, cost2);

        assertEquals(3, Book.getBooks().size());

        String path = "test/file";
        Book.writeObjectsToFile(path);
        Book.readObjectsFromFile(path);

        assertEquals(6, Book.getBooks().size());
        for(Book book : Book.getBooks()){
            System.out.println(book);
        }
    }
}