package multiInheritance;

import util.InvalidFieldException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Worker extends Individual implements Serializable{

    private String email;
    private String phoneNumber;

    public Worker(String firstName, String lastName, LocalDateTime dateOfBirth, String email, String phoneNumber) throws InvalidFieldException {
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

    private static boolean validateEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }
}
