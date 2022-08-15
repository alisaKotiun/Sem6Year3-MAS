package multiInheritance;

import util.InvalidFieldException;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book extends Product{

    private static int productionCharge = 100;

    private String ISBN;
    private LocalDateTime publishDate;
    private Worker author;

    public Book(String ISBN, LocalDateTime publishDate, Worker author, String name, double baseCost) throws InvalidFieldException {
        super(name, baseCost);
        setISBN(ISBN);
        setPublishDate(publishDate);
    }

    // *** GETTERS

    public String getISBN() {
        return ISBN;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public Worker getAuthor() {
        return author;
    }

    public static int getProductionCharge() {
        return productionCharge;
    }

    // *** SETTERS

    private void setISBN(String ISBN) throws InvalidFieldException {
        if(ISBN == null || ISBN.isEmpty() || !checkISBN(ISBN)) {
            throw new InvalidFieldException("ISBN is incorrect");
        }
        this.ISBN = ISBN;
    }

    public void setPublishDate(LocalDateTime publishDate) throws InvalidFieldException {
        if(publishDate == null || publishDate.isAfter(LocalDateTime.now())) {
            throw new InvalidFieldException("Publish Date field is mandatory");
        }
        this.publishDate = publishDate;
    }

    public void setAuthor(Worker author) throws InvalidFieldException {
        if(author == null) {
            throw new InvalidFieldException("Author field is mandatory");
        }
        this.author = author;
    }

    // *** ADDITIONAL LOGIC

    public static boolean checkISBN(String ISBN) {
        String regex = "^\\d{13}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ISBN);
        return matcher.matches();
    }

    @Override
    public double finalCost() {
        return this.getBaseCost() + this.getBaseCost() * productionCharge / 100;
    }
}
