package overlapping;

import util.InvalidFieldException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

interface IBestseller {
    // only for Bestsellers -> check all the books, sort by date and show the ranking of the specific book
    static void ranking(Book book) throws InvalidFieldException {
        if(!book.getGenres().contains(Genre.BESTSELLER)){
            throw new InvalidFieldException("Book is not a bestseller!");
        }
        List<Book> result = Book.getBooks()
                .stream()
                .filter(b -> b.getGenres().contains(Genre.BESTSELLER))
                .sorted(Comparator.comparing(Book::getPublishDate))
                .collect(Collectors.toList());
        System.out.println("Ranking: " + (result.indexOf(book) + 1));
    }
}
