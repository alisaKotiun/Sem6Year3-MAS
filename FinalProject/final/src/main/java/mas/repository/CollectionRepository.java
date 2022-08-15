package mas.repository;

import mas.model.Book;
import mas.model.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionRepository extends CrudRepository<Collection, Long> {
    @Query("from Book as b where b.collection.id = :id")
    public List<Book> findBooksById(@Param("id") Long id);

    public List<Collection> findAll();

    public Optional<Collection> findCollectionById(Long id);
}
