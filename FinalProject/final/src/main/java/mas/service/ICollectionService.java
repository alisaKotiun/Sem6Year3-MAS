package mas.service;

import mas.model.Book;
import mas.model.Collection;
import mas.model.CollectionStatus;

import java.util.List;

public interface ICollectionService {
    public abstract List<Collection> findAll();
    public abstract List<Book> findAllBooks(Long id);
    public abstract void changeStatus(Long id, CollectionStatus status);
    public abstract Double calculatePrice(Long id);
}
