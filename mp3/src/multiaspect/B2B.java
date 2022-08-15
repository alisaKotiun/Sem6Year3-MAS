package multiaspect;

import util.Address;
import util.InvalidFieldException;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B2B extends Customer {
    private static double B2BDiscount = 20;

    private String name;
    private String REGON;
    private CustomerType type;

    // for External
    private Distributor distributor;

    private static Set<String> REGONs = new HashSet<>();

    //for External
    public B2B(Address address, String email, String phoneNumber, String name, String REGON, CustomerType type, Distributor distributor) throws InvalidFieldException {
        super(address, email, phoneNumber);
        setName(name);
        setREGON(REGON);
        setType(type);
        setDistributor(distributor);

        REGONs.add(REGON);
    }

    //for Internal
    public B2B(Address address, String email, String phoneNumber, String name, String REGON, CustomerType type) throws InvalidFieldException {
        this(address, email, phoneNumber,  name, REGON, type, null);
    }

    // *** GETTERS

    public String getName() {
        return name;
    }

    public String getREGON() {
        return REGON;
    }

    public CustomerType getType() {
        return type;
    }

    public Distributor getDistributor() throws InvalidFieldException {
        if(this.type != CustomerType.EXTERNAL){
            throw new InvalidFieldException("Not Allowed!");
        }
        return distributor;
    }

    // *** SETTERS

    public void setName(String name) throws InvalidFieldException {
        if(name == null || name.isEmpty()){
            throw new InvalidFieldException("Name field is mandatory");
        }
        this.name = name;
    }

    public void setREGON(String REGON) throws InvalidFieldException {
        if(REGON == null || REGON.isEmpty() || !checkREGON(REGON)) {
            throw new InvalidFieldException("REGON is incorrect");
        }
        this.REGON = REGON;
    }

    private void setType(CustomerType type) throws InvalidFieldException {
        if(type == null){
            throw new InvalidFieldException("Type field is mandatory");
        }
        this.type = type;
    }

    public void setDistributor(Distributor distributor) throws InvalidFieldException {
        if(this.type != CustomerType.EXTERNAL || distributor == null){
            throw new InvalidFieldException("Not Allowed!");
        }
        this.distributor = distributor;
    }

    // *** ADDITIONAL LOGIC

    public static boolean checkREGON(String REGON) {
        String regex = "^\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(REGON);
        return matcher.matches() && !REGONs.contains(REGON);
    }

    private double calculateDiscount(double price, double discount){
        return price * discount / 100;
    }

    @Override
    public double calculatePriceWithDiscount(double price) {
        if(this.type == CustomerType.EXTERNAL) {
            return price - calculateDiscount(price, B2BDiscount / 2);
        }
        return price - calculateDiscount(price, B2BDiscount);
    }
}
