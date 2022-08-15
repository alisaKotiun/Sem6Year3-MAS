package customConstraint;

import helper.InvalidFieldException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class OrderedBook {
    private static Set<OrderedBook> orderedBooks = new HashSet<>();

    private Order order;
    private Book book;
    private int quantity;

    public OrderedBook(Order order, Book book, int quantity) throws InvalidFieldException {
        if(!isUnique(order, book)) throw new InvalidFieldException("Duplicate attempt");
        if(quantityIsLess(book, quantity)) throw new InvalidFieldException("Quantity for this book is less than" + book.getPriority().getMinQuantity());
        if(costIsLess(book, quantity)) throw new InvalidFieldException("Cost amount for this book is less than " + book.getPriority().getMinCost());

        setOrder(order);
        setBook(book);
        setQuantity(quantity);

        orderedBooks.add(this);
    }

    // *** GETTERS

    public Order getOrder() {
        return order;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public static Set<OrderedBook> getOrderedBooks() {
        return Collections.unmodifiableSet(orderedBooks);
    }

    // *** SETTERS

    public void setOrder(Order order) throws InvalidFieldException {
        if(this.order == order) return;

        if(this.order == null && order != null) {
            this.order = order;
            order.addOrderedBook(this);
            return;
        }

        if(this.order != null && order != null) {
            if(!isUnique(order, this.book)) throw new InvalidFieldException("Duplicate attempt");

            Order tmp = this.order;
            this.order = null;
            tmp.removeOrderedBook(this);

            setOrder(order);
            return;
        }

        if(this.order != null && order == null) {
            remove();
        }
    }

    public void setBook(Book book) throws InvalidFieldException {
        if(this.book == book) return;

        if(this.book == null && book != null) {
            this.book = book;
            book.addOrderedBook(this);
            return;
        }

        if(this.book != null && book != null) {
            if(!isUnique(this.order, book)) throw new InvalidFieldException("Duplicate attempt");

            Book tmp = this.book;
            this.book = null;
            tmp.removeOrderedBook(this);

            setBook(book);
            return;
        }

        if(this.book != null && book == null) {
            remove();
        }
    }

    public void remove() throws InvalidFieldException {
        orderedBooks.remove(this);
        removeOrder();
        removeBook();
    }

    private void removeOrder() throws InvalidFieldException {
        Order tmp = this.order;
        this.order = null;
        tmp.removeOrderedBook(this);
    }

    private void removeBook() throws InvalidFieldException {
        Book tmp = this.book;
        this.book = null;
        tmp.removeOrderedBook(this);
    }

    public void setQuantity(int quantity) throws InvalidFieldException {
        if(quantity < 1){
            throw new InvalidFieldException("Quantity is less than 1");
        }
        this.quantity = quantity;
    }

    // *** ADDITIONAL LOGIC

    private static boolean isUnique(Order order, Book book) {
        if(order == null || book == null) return false;
        return orderedBooks.stream().
                noneMatch(ob -> ob.order.equals(order) && ob.book.equals(book));
    }

    private boolean costIsLess(Book book, int quantity) {
        double amount = book.getCost() * quantity;
        return amount < book.getPriority().getMinCost();
    }

    private boolean quantityIsLess(Book book, int quantity) {
        return quantity < book.getPriority().getMinQuantity();
    }
}
