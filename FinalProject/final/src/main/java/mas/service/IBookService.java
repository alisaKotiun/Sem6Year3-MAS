package mas.service;

import mas.model.Book;

import java.util.Date;
import java.util.List;

public interface IBookService {
    public abstract List<Book> findBooksPublishedAfter(Date date);
    public abstract List<Book> findBooksByISBN(String ISBN);
    public abstract Book save(Book book);
}
