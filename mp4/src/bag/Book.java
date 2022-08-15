package bag;

import helper.InvalidFieldException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book {
    private String ISBN;
    private String name;
    private LocalDateTime publishDate;

    private static List<Comment> comments = new ArrayList<>();

    public Book(String ISBN, String name, LocalDateTime publishDate) {
        this.ISBN = ISBN;
        this.name = name;
        this.publishDate = publishDate;
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

    // *** SETTERS

    private void setISBN(String ISBN) throws InvalidFieldException {
        if(ISBN == null || ISBN.isEmpty()) {
            throw new InvalidFieldException("ISBN is mandatory");
        }
        if(!(checkNewISBN(ISBN) || checkOldISBN(ISBN))) {
            throw new InvalidFieldException("ISBN format is incorrect");
        }

        if(checkOldISBN(ISBN)) {
            this.ISBN = ISBN + "978";
        } else {
            this.ISBN = ISBN;
        }
    }

    public void setName(String name) throws InvalidFieldException {
        if(name == null || name.isEmpty()){
            throw new InvalidFieldException("Name field is mandatory");
        }
        this.name = name;
    }

    public void setPublishDate(LocalDateTime publishDate) throws InvalidFieldException {
        if(publishDate == null || publishDate.isAfter(LocalDateTime.now())) {
            throw new InvalidFieldException("Publish Date field is mandatory");
        }
        this.publishDate = publishDate;
    }

    // *** ADDITIONAL LOGIC

    private static boolean checkNewISBN(String ISBN) {
        String regex = "^\\d{13}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ISBN);
        return matcher.matches();
    }

    private static boolean checkOldISBN(String ISBN) {
        String regex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ISBN);
        return matcher.matches();
    }

    protected void addComment(Comment comment) {
        if(comment != null && !comments.contains(comment) && comment.getBook() == this) {
            comments.add(comment);
            comment.setBook(this);
        }
    }

    public void removeComment(Comment comment) {
        if(comment != null && comments.contains(comment)) {
            comments.remove(comment);
            comment.setBook(null);
        }
    }
}
