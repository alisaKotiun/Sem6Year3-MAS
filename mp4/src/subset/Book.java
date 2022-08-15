package subset;

import helper.InvalidFieldException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book {
    private static List<Book> books = new ArrayList<>();

    private String ISBN;
    private String name;
    private LocalDateTime publishDate;
    private byte[] picture;
    private Collection collection;
    private boolean isCover;

    public Book(String ISBN, String name, LocalDateTime publishDate, String filePath) throws InvalidFieldException {
        setISBN(ISBN);
        setName(name);
        setPublishDate(publishDate);
        setPicture(filePath);
        isCover = false;

        books.add(this);
    }

    // *** GETTERS

    public String getISBN() {
        return ISBN;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public Collection getCollection() {
        return collection;
    }

    public byte[] getPicture() {
        return picture;
    }

    // *** SETTERS

    private void setISBN(String ISBN) throws InvalidFieldException {
        if(ISBN == null || ISBN.isEmpty() || !checkISBN(ISBN)) {
            throw new InvalidFieldException("ISBN is incorrect");
        }
        this.ISBN = ISBN;
    }

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

    public void setPicture(String filePath) throws InvalidFieldException {
        if(filePath == null || filePath.isEmpty()){
            throw new InvalidFieldException("Path field is mandatory");
        }

        try {
            // file to bytes[]
            this.picture = Files.readAllBytes(Paths.get(filePath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // *** ADDITIONAL LOGIC

    private static boolean checkISBN(String ISBN) {
        String regex = "^\\d{13}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ISBN);
        return matcher.matches();
    }

    public void setCollection(Collection collection) {
        if(this.collection == collection) return;

        if(this.collection == null && collection != null) {
            this.collection = collection;
            collection.addBook(this);
            return;
        }

        if(this.collection != null && collection == null) {
            Collection tmp = this.collection;
            this.collection = null;
            tmp.removeBook(this);
            return;
        }

        if(this.collection != null && collection != null) {
            Collection tmp = this.collection;
            this.collection = null;
            tmp.removeBook(this);

            setCollection(collection);
        }
    }

    public void removeCollection() {
        setCollection(null);
    }

    public void setCover(boolean cover) {
        if(this.collection != null && isCover != cover) {
            if(cover) {
                isCover = true;
                collection.setCover(this);
            } else {
                isCover = false;
                collection.removeCover();
            }
        }
    }
}
