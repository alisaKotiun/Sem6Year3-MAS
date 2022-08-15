package mas.service;

import mas.model.Book;
import mas.model.LoyaltyStatus;
import mas.model.Order;

import java.util.List;

public interface ICustomerService {
    public abstract Integer calculateMaxDiscount(Long id);
    public abstract void changeStatus(Long id, LoyaltyStatus status);
    public abstract List<Order> findCurrentOrders(Long id);
    public abstract Double calculatePrice(Long id, Long bookId);

}
