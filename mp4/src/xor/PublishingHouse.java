package xor;

import helper.Address;
import helper.InvalidFieldException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PublishingHouse {

    private static List<PublishingHouse> publishingHouses = new ArrayList<>();

    private String name;
    private Address address;
    private String email;
    private Set<Author> authors = new HashSet<>();

    public PublishingHouse(String name, Address address, String email) throws InvalidFieldException {
        setName(name);
        setAddress(address);
        setEmail(email);

        publishingHouses.add(this);
    }

    // *** GETTERS

    public static List<PublishingHouse> getPublishingHouses() {
        return Collections.unmodifiableList(publishingHouses);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address.toString();
    }

    public String getEmail() {
        return email;
    }

    public Set<Author> getAuthors() {
        return Collections.unmodifiableSet(authors);
    }

    // *** SETTERS

    public void setName(String name) throws InvalidFieldException {
        if(name == null || name.isEmpty()){
            throw new InvalidFieldException("Name field is mandatory");
        }
        this.name = name;
    }

    public void setAddress(Address address) throws InvalidFieldException {
        if(address == null){
            throw new InvalidFieldException("general.Address field is mandatory");
        }
        this.address = address;
    }

    public void setEmail(String email) throws InvalidFieldException {
        if(email == null || email.isEmpty() || !validateEmail(email)){
            throw new InvalidFieldException("Email field is mandatory");
        }
        this.email = email;
    }

    // *** ADDITIONAL LOGIC

    private boolean validateEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }

    public void addAuthor(Author author) throws InvalidFieldException {
        if(author != null && !authors.contains(author)) {
            authors.add(author);
            author.changePublishingHouse(this);
        }
    }

    protected void removeAuthor(Author author) throws InvalidFieldException {
        if(author != null && authors.contains(author)) {
            authors.remove(author);
            author.removePublishingHouse();
        }
    }
}
