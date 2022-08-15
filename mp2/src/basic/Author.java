package basic;

import general.Individual;
import util.InvalidFieldException;
import util.Util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Author extends Individual implements Serializable{
    private static List<Author> authors = new ArrayList<>();

    private String email;
    private String phoneNumber;
    private PublishingHouse publishingHouse;

    public Author(String firstName, String middleName, String lastName, LocalDateTime dateOfBirth, String email, String phoneNumber) throws InvalidFieldException {
        super(firstName, middleName, lastName, dateOfBirth);
        setEmail(email);
        setPhoneNumber(phoneNumber);

        authors.add(this);
    }

    public Author(String firstName, String lastName, LocalDateTime dateOfBirth, String email, String phoneNumber) throws InvalidFieldException {
        this(firstName, null, lastName, dateOfBirth, email, phoneNumber);
    }

    // *** GETTERS

    public static List<Author> getAuthors() {
        return Collections.unmodifiableList(authors);
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    // *** SETTERS

    public void setEmail(String email) throws InvalidFieldException {
        if(email == null || email.isEmpty() || !Util.validateEmail(email)){
            throw new InvalidFieldException("Email field is mandatory");
        }
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) throws InvalidFieldException {
        if(phoneNumber == null || phoneNumber.isEmpty()){
            throw new InvalidFieldException("Phone field is mandatory");
        }
        this.phoneNumber = phoneNumber;
    }

    // *** ADDITIONAL LOGIC

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        if(this.publishingHouse == publishingHouse) return;

        if(this.publishingHouse == null && publishingHouse != null) {
            this.publishingHouse = publishingHouse;
            publishingHouse.addAuthor(this);
            return;
        }

        if(this.publishingHouse != null && publishingHouse == null) {
            PublishingHouse tmp = this.publishingHouse;
            this.publishingHouse = null;
            tmp.removeAuthor(this);
            return;
        }

        if(this.publishingHouse != null && publishingHouse != null) {
            PublishingHouse tmp = this.publishingHouse;
            this.publishingHouse = null;
            tmp.removeAuthor(this);

            setPublishingHouse(publishingHouse);
        }
    }

    public void removePublishingHouse() {
        setPublishingHouse(null);
    }

    @Override
    public String toString() {
        return "basic.Author{" +
                "firstName='" + getFirstName() + '\'' +
                ", middleName='" + getMiddleName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                '}';
    }
}
