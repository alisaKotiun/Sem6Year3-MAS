package mas.repository;

import mas.model.Customer;
import mas.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    public List<Order> findOrderByCustomerIs(Customer customer);
    public Optional<Order> findOrderById(Long id);
}
