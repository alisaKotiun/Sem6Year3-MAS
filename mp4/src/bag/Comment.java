package bag;

import helper.InvalidFieldException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Comment {
    private static List<Comment> comments = new ArrayList<>();

    private String content;
    private Book book;
    private Customer customer;

    public Comment(String content, Book book, Customer customer) throws InvalidFieldException {
        if(customer == null || book == null) {
            throw new InvalidFieldException("Book and Customer are mandatory!");
        }

        setContent(content);
        setBook(book);
        setCustomer(customer);

        comments.add(this);
    }

    // *** GETTERS

    public String getContent() {
        return content;
    }

    public Book getBook() {
        return book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public static List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }

    // *** SETTERS

    public void setContent(String content) throws InvalidFieldException {
        if(content == null || content.isEmpty()){
            throw new InvalidFieldException("Name field is mandatory");
        }
        this.content = content;
    }

    protected void setBook(Book book) {
        if(this.book == book) return;

        if(this.book == null && book != null) {
            this.book = book;
            book.addComment(this);
            return;
        }

        if(this.book != null && book == null) {
            remove();
        }
    }

    protected void setCustomer(Customer customer) {
        if(this.customer == customer) return;

        if(this.customer == null && customer != null) {
            this.customer = customer;
            customer.addComment(this);
            return;
        }

        if(this.customer != null && customer == null) {
            remove();
        }
    }

    public void remove() {
        comments.remove(this);
        removeBook();
        removeCustomer();
    }

    private void removeBook() {
        Book tmp = this.book;
        this.book = null;
        tmp.removeComment(this);
    }

    private void removeCustomer() {
        Customer tmp = this.customer;
        this.customer = null;
        tmp.removeComment(this);
    }
}
