package mas.service;

import lombok.RequiredArgsConstructor;
import mas.model.Book;
import mas.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService{

    private final BookRepository bookRepository;

    @Override
    public List<Book> findBooksPublishedAfter(Date date) {
        return bookRepository.findBooksPublishedAfter(date);
    }

    @Override
    public List<Book> findBooksByISBN(String ISBN) {
        return bookRepository.findBookByISBN(ISBN);
    }

    @Override
    public Book save(Book book) {
        bookRepository.save(book);
        return book;
    }
}
