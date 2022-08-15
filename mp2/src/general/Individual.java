package general;

import util.InvalidFieldException;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Individual implements Serializable{

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDateTime dateOfBirth;

    public Individual(String firstName, String middleName, String lastName, LocalDateTime dateOfBirth) throws InvalidFieldException {
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
    }

    // *** GETTERS

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    // *** SETTERS

    public void setFirstName(String firstName) throws InvalidFieldException {
        if(firstName == null || firstName.isEmpty()){
            throw new InvalidFieldException("First name field is mandatory");
        }
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) throws InvalidFieldException {
        if(lastName == null || lastName.isEmpty()){
            throw new InvalidFieldException("Last name field is mandatory");
        }
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) throws InvalidFieldException {
        if(dateOfBirth == null || dateOfBirth.isAfter(LocalDateTime.now())){
            throw new InvalidFieldException("Date of birth is mandatory");
        }
        this.dateOfBirth = dateOfBirth;
    }
}
