package subset;

import helper.InvalidFieldException;

import java.util.*;

public class Collection {

    private static List<Collection> collections = new ArrayList<>();

    private String name;
    private CollectionStatus status;
    private Set<Book> books = new HashSet<>();
    private Book cover;

    public Collection(String name, CollectionStatus status) throws InvalidFieldException {
        setName(name);
        setStatus(status);

        collections.add(this);
    }

    public Collection(String name) throws InvalidFieldException {
        this(name, CollectionStatus.PLANNED);
    }

    // *** GETTERS

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status.getName();
    }

    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(books);
    }

    public byte[] getCover() {
        if(cover == null) {
            return null;
        }
        return cover.getPicture();
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
        if(book != null && !books.contains(book)) {
            books.add(book);
            book.setCollection(this);
        }
    }

    public void removeBook(Book book) {
        if(book != null && books.contains(book)) {
            if(book == cover) {
                removeCover();
            }

            books.remove(book);
            book.removeCollection();
        }
    }

    public void setCover(Book book) {
        if(this.cover == book) return;

        if(this.cover == null && book != null && books.contains(book)) {
            this.cover = book;
            book.setCover(true);
            return;
        }

        if(this.cover != null && book == null) {
            Book tmp = this.cover;
            this.cover = null;
            tmp.setCover(false);
            return;
        }

        if(this.cover != null && book != null && books.contains(book)) {
            Book tmp = this.cover;
            this.cover = null;
            tmp.setCover(false);

            setCover(book);
        }
    }

    public void removeCover() {
        setCover(null);
    }
}
