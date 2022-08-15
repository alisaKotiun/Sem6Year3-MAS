package mas.service;

import mas.model.Book;

public interface IIndividualService {
    public abstract Double calculatePrice(Long id, Long bookId);
}
