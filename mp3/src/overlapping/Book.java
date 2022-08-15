package overlapping;

import util.InvalidFieldException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book implements IBestseller {
    private String ISBN;
    private String name;
    private LocalDateTime publishDate;
    private double baseCost;

    private static List<Book> books = new ArrayList<>();

    private EnumSet<Genre> genres;

    public Book(String ISBN, String name, LocalDateTime publishDate, double baseCost, EnumSet<Genre> genres) throws InvalidFieldException {
        setISBN(ISBN);
        setName(name);
        setPublishDate(publishDate);
        setBaseCost(baseCost);
        setGenres(genres);

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

    public double getBaseCost() {
        return baseCost;
    }

    public Set<Genre> getGenres() {
        return Collections.unmodifiableSet(genres);
    }

    public static List<Book> getBooks() {
        return Collections.unmodifiableList(books);
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

    private void setGenres(EnumSet<Genre> genres) throws InvalidFieldException {
        if(genres == null){
            throw new InvalidFieldException("Genres field is mandatory");
        }

        EnumSet<Genre> newGenres = EnumSet.noneOf(Genre.class);
        for(Genre genre : genres){
            if(genre != null){
                newGenres.add(genre);
            }
        }

        if (newGenres.isEmpty()){
            throw new InvalidFieldException("Genres field is mandatory");
        }
        this.genres = newGenres;
    }

    // *** ADDITIONAL LOGIC

    public static boolean checkISBN(String ISBN) {
        String regex = "^\\d{13}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ISBN);
        return matcher.matches();
    }

    public int getAgeRestriction() {
        Genre genre = genres
                .stream()
                .max(Comparator.comparing(Genre::getAgeRestriction))
                .orElseThrow(NoSuchElementException::new);
        return genre.getAgeRestriction();
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }

    public void ranking() throws InvalidFieldException {
        IBestseller.ranking(this);
    }
}
