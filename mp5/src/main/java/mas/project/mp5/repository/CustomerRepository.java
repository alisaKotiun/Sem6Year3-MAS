package mas.project.mp5.repository;

import mas.project.mp5.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("from Customer as c left join fetch c.orders where c.id = :id")
    public Optional<Customer> findByIdWithOrders (@Param("id") long id);
}
