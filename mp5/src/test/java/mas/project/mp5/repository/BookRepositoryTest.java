package mas.project.mp5.repository;

import mas.project.mp5.model.Book;
import mas.project.mp5.model.Cost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Book book;

    @BeforeEach
    public void initData() {
        book = Book.builder()
                .ISBN("1234567890123")
                .name("Book" + LocalDateTime.now())
                .publishDate(Date.valueOf("2002-04-13"))
                .cost(new Cost(10., true))
                .genres(Set.of("Comedy", "Romance"))
                .build();
    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(bookRepository);
    }

    @Test
    public void testSaveBooks() {
        bookRepository.save(book);
        entityManager.flush();

        long count = bookRepository.count();
        assertEquals(1, count);
    }

    @Test
    public void testGetByName() {
        bookRepository.save(book);
        entityManager.flush();
        List<Book> books = bookRepository.findBooksByNameStartingWith("Boo");
        assertEquals(1, books.size());
    }

    @Test
    public void testFindWithDateAfter() {
        bookRepository.save(book);
        entityManager.flush();
        List<Book> books = bookRepository.findBooksPublishedAfter(Date.valueOf("2003-04-13"));
        assertEquals(0, books.size());
    }
}