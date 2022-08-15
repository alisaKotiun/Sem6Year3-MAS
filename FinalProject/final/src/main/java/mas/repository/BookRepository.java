package mas.repository;

import mas.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    public List<Book> findAll();

    public Optional<Book> findBookById(Long id);

    @Query("from Book as b where b.publishDate > :someDate")
    public List<Book> findBooksPublishedAfter(@Param("someDate") Date someDate);

    public List<Book> findBookByISBN(String ISBN);

}
