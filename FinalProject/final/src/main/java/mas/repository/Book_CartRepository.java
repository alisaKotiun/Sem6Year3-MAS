package mas.repository;

import mas.model.Book_Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Book_CartRepository extends CrudRepository<Book_Cart, Long> {
}
