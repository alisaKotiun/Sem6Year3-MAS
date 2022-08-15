package mas.project.mp5.repository;

import mas.project.mp5.model.BOrder;
import mas.project.mp5.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository <Book, Long> {

    public Optional<Book> findBookById(Long id);

    public List<Book> findBooksByNameStartingWith(String name);

    @Query("from Book as b where b.publishDate > :someDate")
    public List<Book> findBooksPublishedAfter(@Param("someDate")Date someDate);

    @Query("from Book as b left join fetch b.orderedBooks where b.id = :id")
    public Optional<Book> findByIdWithBookOrders (@Param("id") long id);

}
