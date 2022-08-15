package xor;

import helper.InvalidFieldException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EPublisher {

    private static List<EPublisher> ePublishers = new ArrayList<>();

    private String name;
    private String website;
    private String email;
    private Set<Author> authors = new HashSet<>();

    public EPublisher(String name, String website, String email) throws InvalidFieldException {
        setName(name);
        setWebsite(website);
        setEmail(email);

        ePublishers.add(this);
    }

    // *** GETTERS

    public static List<EPublisher> getEPublishers() {
        return Collections.unmodifiableList(ePublishers);
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
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

    public void setWebsite(String website) throws InvalidFieldException {
        if(website == null || website.isEmpty()){
            throw new InvalidFieldException("Name field is mandatory");
        }
        this.website = website;
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
            author.changeEPublisher(this);
        }
    }

    protected void removeAuthor(Author author) throws InvalidFieldException {
        if(author != null && authors.contains(author)) {
            authors.remove(author);
            author.removeEPublisher();
        }
    }
}
