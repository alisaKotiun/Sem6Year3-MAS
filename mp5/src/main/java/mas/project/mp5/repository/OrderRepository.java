package mas.project.mp5.repository;

import mas.project.mp5.model.BOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<BOrder, Long> {
    @Query("from BOrder as o left join fetch  o.orderedBooks where o.id = :id")
    public Optional<BOrder> findByIdWithBookOrders (@Param("id") long id);
}
