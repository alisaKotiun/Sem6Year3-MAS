package mas.project.mp5.repository;

import mas.project.mp5.model.OrderedBook;
import org.springframework.data.repository.CrudRepository;

public interface OrderedBookRepository extends CrudRepository<OrderedBook, Long> {
}
