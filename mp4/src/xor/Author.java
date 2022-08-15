package xor;

import helper.Individual;
import helper.InvalidFieldException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Author extends Individual {
    private static List<Author> authors = new ArrayList<>();

    private String email;
    private String phoneNumber;
    private PublishingHouse publishingHouse;
    private EPublisher ePublisher;

    public Author(String firstName, String lastName, LocalDateTime dateOfBirth, String email, String phoneNumber, PublishingHouse publishingHouse) throws InvalidFieldException {
        super(firstName, lastName, dateOfBirth);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        changePublishingHouse(publishingHouse);

        authors.add(this);
    }

    public Author(String firstName, String lastName, LocalDateTime dateOfBirth, String email, String phoneNumber, EPublisher ePublisher) throws InvalidFieldException {
        super(firstName, lastName, dateOfBirth);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        changeEPublisher(ePublisher);

        authors.add(this);
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

    public EPublisher getEPublisher() {
        return ePublisher;
    }

    // *** SETTERS

    public void setEmail(String email) throws InvalidFieldException {
        if(email == null || email.isEmpty() || !validateEmail(email)){
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

    private boolean validateEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }

    public void changePublishingHouse(PublishingHouse publishingHouse) throws InvalidFieldException {
        if(publishingHouse == null) throw new InvalidFieldException("Publishing House can't be null");
        if(this.ePublisher != null) {
            removeEPublisher();
        }
        setPublishingHouse(publishingHouse);
    }

    public void changeEPublisher(EPublisher ePublisher) throws InvalidFieldException {
        if(ePublisher == null) throw new InvalidFieldException("EPublisher can't be null");
        if(this.publishingHouse != null) {
            removePublishingHouse();
        }
        setEPublisher(ePublisher);
    }

    //

    private void setPublishingHouse(PublishingHouse publishingHouse) throws InvalidFieldException {
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

    protected void removePublishingHouse() throws InvalidFieldException {
        setPublishingHouse(null);
    }

    private void setEPublisher (EPublisher ePublisher) throws InvalidFieldException {
        if(this.ePublisher == ePublisher) return;

        if(this.ePublisher == null && ePublisher != null) {
            this.ePublisher = ePublisher;
            ePublisher.addAuthor(this);
            return;
        }

        if(this.ePublisher != null && ePublisher == null) {
            EPublisher tmp = this.ePublisher;
            this.ePublisher = null;
            tmp.removeAuthor(this);
            return;
        }

        if(this.ePublisher != null && ePublisher != null) {
            EPublisher tmp = this.ePublisher;
            this.ePublisher = null;
            tmp.removeAuthor(this);

            setEPublisher(ePublisher);
        }
    }

    protected void removeEPublisher() throws InvalidFieldException {
        setEPublisher(null);
    }
}
