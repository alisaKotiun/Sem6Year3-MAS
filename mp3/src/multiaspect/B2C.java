package multiaspect;

import util.Address;
import util.InvalidFieldException;

import java.time.LocalDateTime;

public class B2C extends Customer{
    private static double B2CDiscount = 10;

    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
    private CustomerType type;

    //for External
    private Distributor deliveryCompany;
    private int region; // depending on how far from Poland {1, 2, 3}

    //for Internal
    private boolean bankTransferPayment;

    //for External
    public B2C(Address address, String email, String phoneNumber, String firstName, String lastName, LocalDateTime dateOfBirth, CustomerType type, Distributor deliveryCompany, int region) throws InvalidFieldException {
        super(address, email, phoneNumber);
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
        setType(type);
        setDeliveryCompany(deliveryCompany);
        setRegion(region);
    }

    //for Internal
    public B2C(Address address, String email, String phoneNumber, String firstName, String lastName, LocalDateTime dateOfBirth, CustomerType type, boolean bankTransferPayment) throws InvalidFieldException {
        super(address, email, phoneNumber);
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
        setType(type);
        setBankTransferPayment(bankTransferPayment);
    }

    // *** GETTERS

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public CustomerType getType() {
        return type;
    }

    public Distributor getDeliveryCompany() throws InvalidFieldException {
        if(this.type != CustomerType.EXTERNAL){
            throw new InvalidFieldException("Not Allowed!");
        }
        return deliveryCompany;
    }

    public int getRegion() throws InvalidFieldException {
        if(this.type != CustomerType.EXTERNAL){
            throw new InvalidFieldException("Not Allowed!");
        }
        return region;
    }

    public boolean isBankTransferPayment() throws InvalidFieldException {
        if(this.type != CustomerType.INTERNAL){
            throw new InvalidFieldException("Not Allowed!");
        }
        return bankTransferPayment;
    }

    // *** SETTERS

    public void setFirstName(String firstName) throws InvalidFieldException {
        if(firstName == null || firstName.isEmpty()){
            throw new InvalidFieldException("First name field is mandatory");
        }
        this.firstName = firstName;
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

    private void setType(CustomerType type) throws InvalidFieldException {
        if(type == null){
            throw new InvalidFieldException("Type field is mandatory");
        }
        this.type = type;
    }

    public void setDeliveryCompany(Distributor deliveryCompany) throws InvalidFieldException {
        if(this.type != CustomerType.EXTERNAL || deliveryCompany == null){
            throw new InvalidFieldException("Not Allowed!");
        }
        this.deliveryCompany = deliveryCompany;
    }

    public void setRegion(int region) throws InvalidFieldException {
        if(this.type != CustomerType.EXTERNAL || !(region > 0 && region < 4)){
            throw new InvalidFieldException("Not Allowed!");
        }
        this.region = region;
    }

    public void setBankTransferPayment(boolean bankTransferPayment) throws InvalidFieldException {
        if(this.type != CustomerType.INTERNAL){
            throw new InvalidFieldException("Not Allowed!");
        }
        this.bankTransferPayment = bankTransferPayment;
    }

    // *** ADDITIONAL LOGIC

    private double calculateDiscountEx(double price) throws InvalidFieldException {
        double result = 0;
        switch (region) {
            case 1:
                result = price * (B2CDiscount / 2) / 100;
                break;
            case 2:
                result = price * (B2CDiscount / 3) / 100;
                break;
            case 3:
                result = price * (B2CDiscount / 4) / 100;
                break;
        }
        return result;
    }

    private double calculateDiscountIn(double price){
        if(bankTransferPayment) {
            return 0;
        }
        return price * B2CDiscount / 100;
    }

    @Override
    public double calculatePriceWithDiscount(double price) throws InvalidFieldException {
        if(this.type == CustomerType.EXTERNAL) {
            return price - calculateDiscountEx(price);
        }
        return price - calculateDiscountIn(price);
    }
}
