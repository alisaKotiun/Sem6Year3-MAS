package customConstraint;

import helper.InvalidFieldException;

import java.time.LocalDateTime;
import java.util.*;

public class Book {

    private static List<Book> books = new ArrayList<>();

    private String name;
    private LocalDateTime publishDate;
    private double cost;
    private Priority priority;

    private Set<OrderedBook> orderedBooks = new HashSet<>();

    public Book(String name, LocalDateTime publishDate, int cost, Priority priority) throws InvalidFieldException {
        setName(name);
        setPublishDate(publishDate);
        setCost(cost);
        setPriority(priority);

        books.add(this);
    }

    // *** GETTERS

    public String getName() {
        return name;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public double getCost() {
        return cost;
    }

    public Priority getPriority() {
        return priority;
    }

    public Set<OrderedBook> getOrderedBooks() {
        return Collections.unmodifiableSet(orderedBooks);
    }

    // *** SETTERS

    public void setName(String name) throws InvalidFieldException {
        if(name == null || name.isEmpty()){
            throw new InvalidFieldException("Name field is mandatory");
        }
        this.name = name;
    }

    public void setPublishDate(LocalDateTime publishDate) throws InvalidFieldException {
        if(publishDate == null) {
            throw new InvalidFieldException("Publish Date field is mandatory");
        }
        this.publishDate = publishDate;
    }

    public void setCost(double cost) throws InvalidFieldException {
        if(cost <= 0){
            throw new InvalidFieldException("Cost is mandatory");
        }
        this.cost = cost;
    }

    public void setPriority(Priority priority) throws InvalidFieldException {
        if(priority == null){
            throw new InvalidFieldException("Priority field is mandatory");
        }
        this.priority = priority;
    }

    // *** ADDITIONAL LOGIC

    public void addOrderedBook(OrderedBook orderedBook) throws InvalidFieldException {
        if(orderedBook != null && !orderedBooks.contains(orderedBook)) {
            orderedBooks.add(orderedBook);
            orderedBook.setBook(this);
        }
    }

    public void removeOrderedBook(OrderedBook orderedBook) throws InvalidFieldException {
        if(orderedBook != null && orderedBooks.contains(orderedBook)) {
            orderedBooks.remove(orderedBook);
            orderedBook.setBook(null);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return name.equals(book.name) && publishDate.equals(book.publishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, publishDate);
    }
}
