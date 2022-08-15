import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.Collectors;

public class Book implements Serializable {

    private static List<Book> books = new ArrayList<>();
    private static long discount = 10;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);

    private String name;
    private LocalDateTime publishDate;
    private LocalDateTime creationDate;
    private Set<String> genres;
    private Cost cost;

    public Book(String name, LocalDateTime publishDate, LocalDateTime creationDate, Set<String> genres, Cost cost) throws InvalidFieldException {
        setName(name);
        setPublishDate(publishDate);
        setCreationDate(creationDate);
        setGenres(genres);
        setCost(cost);

        books.add(this);
    }

    public Book(String name, LocalDateTime publishDate, Set<String> genres, Cost cost) throws InvalidFieldException {
        this(name, publishDate, null, genres, cost);
    }

    // *** GETTERS

    @Contract(pure = true)
    public static @NotNull @UnmodifiableView List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public static long getDiscount() {
        return discount;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Set<String> getGenres() {
        return Collections.unmodifiableSet(genres);
    }

    public Cost getCost() {
        return new Cost(cost.getBaseValue(), cost.isWithDiscount());
    }

    public double getFinalPrice() {
        if(this.cost.isWithDiscount()){
            return cost.getBaseValue() - (cost.getBaseValue() * discount / 100);
        }
        return cost.getBaseValue();
    }


    // *** SETTERS

    public static void setDiscount(long discount) {
        if(discount < 0 || discount > 100){
            throw new IllegalArgumentException("Discount value is not in an allowed range[0, 100]");
        }
        Book.discount = discount;
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

    public void setCreationDate(LocalDateTime creationDate) throws InvalidFieldException {
        if(creationDate != null && creationDate.isAfter(LocalDateTime.now())){
            throw new InvalidFieldException("Creation Date cannot be from future");
        }
        this.creationDate = creationDate;
    }

    public void setGenres(Set<String> genres) throws InvalidFieldException {
        if(genres == null){
            throw new InvalidFieldException("Genres field is mandatory");
        }

        Set<String> newGenres = new HashSet<>();
        for(String genre : genres){
            if(genre != null && !genre.isEmpty()){
                newGenres.add(genre);
            }
        }

        if (newGenres.isEmpty()){
            throw new InvalidFieldException("Genres field is mandatory");
        }
        this.genres = newGenres;
    }

    public void setCost(Cost cost) throws InvalidFieldException {
        if(cost == null){
            throw new InvalidFieldException("Cost field is mandatory");
        }
        this.cost = new Cost(cost.getBaseValue(), cost.isWithDiscount());
    }

    // *** ADDITIONAL LOGIC
 //enire
    public static void writeObjectsToFile(String path) throws IOException {
        try( FileOutputStream fileOutputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            for(Book book : books){
                objectOutputStream.writeObject(book);
                objectOutputStream.reset();
            }
        }
    }

    public static void readObjectsFromFile(String path) throws IOException, ClassNotFoundException {
        try( FileInputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            while (fileInputStream.available() > 0){
                Book book = (Book) objectInputStream.readObject();
                books.add(book);
            }
        } catch (EOFException e) {
            // ... this is fine
        }
    }

    public void addGenre(String genre){
        if(genre != null && !genre.isEmpty()){
            genres.add(genre);
        }
    }

    public void removeGenre(String genre){
        if(genres.contains(genre) && genres.size() > 1){
            genres.remove(genre);
        }
    }

    public static List<Book> getBooksByName(String name){
        if(name == null){
            return new ArrayList<Book>();
        }
        String nameToLower = name.toLowerCase(Locale.ROOT);
        return books.stream()
                .filter(book -> book.name.toLowerCase(Locale.ROOT).contains(nameToLower))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "'" + name + "':\n" +
                "\tPublish Date = " + dtf.format(publishDate) +
                (creationDate == null ? "" : "\n\tCreation Date = " + dtf.format(creationDate)) +
                "\n\tGenres = " + Arrays.toString(genres.toArray()) +
                "\n\tFinal Price = " + getFinalPrice() + "$\n";
    }
}
