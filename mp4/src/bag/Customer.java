package bag;

import helper.Individual;
import helper.InvalidFieldException;
import ordered.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer extends Individual {

    private String email;
    private String phoneNumber;
    private static List<Comment> comments = new ArrayList<>();

    public Customer(String firstName, String lastName, LocalDateTime dateOfBirth, String email, String phoneNumber) throws InvalidFieldException {
        super(firstName, lastName, dateOfBirth);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    // *** GETTERS

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // *** SETTERS

    public void setEmail(String email) throws InvalidFieldException {
        if(email != null && !email.isEmpty() && !validateEmail(email)){
            throw new InvalidFieldException("Email format is incorrect");
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

    private static boolean validateEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }

    protected void addComment(Comment comment) {
        if(comment != null && !comments.contains(comment) && comment.getCustomer() == this) {
            comments.add(comment);
            comment.setCustomer(this);
        }
    }

    public void removeComment(Comment comment) {
        if(comment != null && comments.contains(comment)) {
            comments.remove(comment);
            comment.setCustomer(null);
        }
    }
}
