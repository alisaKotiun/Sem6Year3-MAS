package polymorphism;

import util.InvalidFieldException;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Book {

    private String ISBN;
    private String name;
    private LocalDateTime publishDate;
    private double baseCost;

    public Book(String ISBN, String name, LocalDateTime publishDate, double baseCost) throws InvalidFieldException {
        setISBN(ISBN);
        setName(name);
        setPublishDate(publishDate);
        setBaseCost(baseCost);
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

    public double getBaseCost() {
        return baseCost;
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
        if(publishDate == null || publishDate.isAfter(LocalDateTime.now())) {
            throw new InvalidFieldException("Publish Date field is mandatory");
        }
        this.publishDate = publishDate;
    }

    public void setBaseCost(double baseCost) throws InvalidFieldException {
        if(baseCost < 0){
            throw new InvalidFieldException("Cost field is mandatory");
        }
        this.baseCost = baseCost;
    }

    // *** ADDITIONAL LOGIC

    public abstract double finalCost();

    public static boolean checkISBN(String ISBN) {
        String regex = "^\\d{13}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ISBN);
        return matcher.matches();
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
}
