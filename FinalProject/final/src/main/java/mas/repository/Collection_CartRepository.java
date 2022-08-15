package mas.repository;

import mas.model.Collection_Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Collection_CartRepository extends CrudRepository<Collection_Cart, Long> {
}
