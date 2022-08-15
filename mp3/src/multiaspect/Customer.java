package multiaspect;

import util.Address;
import util.InvalidFieldException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Customer {
    private static int counter = 1;

    private int index;
    private Address address;
    private String email;
    private String phoneNumber;

    public Customer(Address address, String email, String phoneNumber) throws InvalidFieldException {
        setAddress(address);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    // *** GETTERS

    public int getIndex() {
        return index;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // *** SETTERS

    public void setIndex(int index) {
        this.index = counter;
        counter += 1;
    }

    public void setAddress(Address address) throws InvalidFieldException {
        if(address == null){
            throw new InvalidFieldException("Address format is incorrect");
        }
        this.address = address;
    }

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

    public abstract double calculatePriceWithDiscount(double price) throws InvalidFieldException;
}
