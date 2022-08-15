package mas.repository;

import mas.model.CustomerMaxDiscount;
import mas.model.LoyaltyStatus;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDiscountRepository extends CrudRepository<CustomerMaxDiscount, Long> {
    public CustomerMaxDiscount findFirstByStatus(LoyaltyStatus status);
}
