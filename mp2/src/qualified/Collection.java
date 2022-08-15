package qualified;

import util.InvalidFieldException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Collection implements Serializable {

    private static List<Collection> collections = new ArrayList<>();

    private String name;
    private CollectionStatus status;
    private Map<String, Book> books = new HashMap<>();

    public Collection(String name, CollectionStatus status) throws InvalidFieldException {
        setName(name);
        setStatus(status);

        collections.add(this);
    }

    public Collection(String name) throws InvalidFieldException {
        this(name, CollectionStatus.PLANNED);
    }

    // *** GETTERS

    public static List<Collection> getCollections() {
        return Collections.unmodifiableList(collections);
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status.getName();
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books.values().stream().collect(Collectors.toList()));
    }

    public Book getBookByISBN(String ISBN) {
        return books.get(ISBN);
    }

    // *** SETTERS

    public void setName(String name) throws InvalidFieldException {
        if(name == null || name.isEmpty()){
            throw new InvalidFieldException("Name field is mandatory");
        }
        this.name = name;
    }

    public void setStatus(CollectionStatus status) throws InvalidFieldException {
        if(status == null){
            throw new InvalidFieldException("Status field is mandatory");
        }
        this.status = status;
    }

    // *** ADDITIONAL LOGIC

    public void addBook(Book book) {
        if(book != null && !isUniqueBook(book)) {
            books.put(book.getISBN(), book);
            book.setCollection(this);
        }
    }

    public void removeBook(Book book) {
        if(book != null && books.containsKey(book.getISBN())) {
            books.remove(book.getISBN(), book);
            book.removeCollection();
        }
    }

    private boolean isUniqueBook(Book book) {
        return books.keySet().stream().anyMatch(b -> b.equals(book.getISBN()));
    }
}
