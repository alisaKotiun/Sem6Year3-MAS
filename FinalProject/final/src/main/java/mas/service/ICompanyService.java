package mas.service;

import mas.model.Book;
import mas.model.LoyaltyStatus;
import mas.model.Order;

import java.util.List;

public interface ICompanyService {
    public abstract Double calculatePrice(Long id, Long bookId);
}
