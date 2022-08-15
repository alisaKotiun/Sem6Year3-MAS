package util;

import java.io.Serializable;

public class Address implements Serializable {

    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private Integer apartment;

    public Address(String country, String city, String street, String houseNumber, Integer apartment) throws InvalidFieldException {
        setCountry(country);
        setCity(city);
        setStreet(street);
        setHouseNumber(houseNumber);
        setApartment(apartment);
    }

    public Address(String country, String city, String street, String houseNumber) throws InvalidFieldException {
        this(country, city, street, houseNumber, null);
    }

    // *** SETTERS

    public void setCountry(String country) throws InvalidFieldException {
        if(country == null || country.isEmpty()){
            throw new InvalidFieldException("Country field is mandatory");
        }
        this.country = country;
    }

    public void setCity(String city) throws InvalidFieldException {
        if(city == null || city.isEmpty()){
            throw new InvalidFieldException("City field is mandatory");
        }
        this.city = city;
    }

    public void setStreet(String street) throws InvalidFieldException {
        if(street == null || street.isEmpty()){
            throw new InvalidFieldException("Street field is mandatory");
        }
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) throws InvalidFieldException {
        if(houseNumber == null || houseNumber.isEmpty()){
            throw new InvalidFieldException("House number field is mandatory");
        }
        this.houseNumber = houseNumber;
    }

    public void setApartment(Integer apartment) throws InvalidFieldException {
        if(apartment != null && apartment <= 0){
            throw new InvalidFieldException("Apartment field is mandatory");
        }
        this.apartment = apartment;
    }

    // *** ADDITIONAL LOGIC

    @Override
    public String toString() {
        return country +
                ", " + city +
                ", " + street +
                " " + houseNumber +
                "/" + apartment;
    }
}
