package dynamic;

import util.Address;
import util.InvalidFieldException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private static List<Order> orders = new ArrayList<>();
    private static int counter = 1;

    private int orderNumber;
    private LocalDateTime creationDate;
    private Address address;
    private Stage stage;

    //shipping stage
    private Employee employee;

    //closed stage (successfully or not)
    private boolean isDelivered;

    public Order(Address address) throws InvalidFieldException {
        setOrderNumber();
        setCreationDate(LocalDateTime.now());
        setAddress(address);
        stage = Stage.PROCESSING;

        orders.add(this);
    }

    // *** GETTERS

    public static List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Address getAddress() {
        return address;
    }

    public Stage getStage() {
        return stage;
    }

    public Employee getEmployee() throws InvalidFieldException {
        if(stage != Stage.SHIPPING) {
            throw new InvalidFieldException("Not Allowed Option!");
        }
        return employee;
    }

    public String isDelivered() {
        if(this.stage != Stage.CLOSED) return "Order is not delivered yet";
        if(isDelivered){
            return "Order is successfully delivered!";
        }
        return "Order delivery was failed";
    }

    // *** SETTERS

    private void setOrderNumber() {
        this.orderNumber = counter;
        counter += 1;
    }

    public void setCreationDate(LocalDateTime creationDate) throws InvalidFieldException {
        if(creationDate == null || creationDate.isAfter(LocalDateTime.now())){
            throw new InvalidFieldException("Creation date is mandatory");
        }
        this.creationDate = creationDate;
    }

    public void setAddress(Address address) throws InvalidFieldException {
        if(address == null){
            throw new InvalidFieldException("Address field is mandatory");
        }
        this.address = address;
    }

    private void setDelivered(Boolean delivered) {
        isDelivered = delivered;
    }

    // *** ADDITIONAL LOGIC

    private void setStage(Stage stage) throws InvalidFieldException {
        if(stage == null) {
            throw new InvalidFieldException("Stage value is invalid");
        }
        this.stage = stage;
    }

    public void changeStageToShipment(Stage stage, Employee employee) throws InvalidFieldException {
        if(stage != Stage.SHIPPING || this.stage != Stage.PROCESSING || employee == null){
            throw new InvalidFieldException("Stage or employee value is invalid");
        }
        setStage(stage);
        setEmployee(employee);
    }

    public void changeStageToClosed(Stage stage, boolean isDelivered) throws InvalidFieldException {
        if(stage != Stage.CLOSED || this.stage == Stage.CLOSED) {
            throw new InvalidFieldException("Invalid parameters!");
        }
        setStage(stage);
        removeEmployee();
        setDelivered(isDelivered);
        orders.remove(this);
    }

    public void setEmployee(Employee employee) throws InvalidFieldException {
        if(this.stage == Stage.PROCESSING){
            throw new InvalidFieldException("Stage value is invalid");
        }

        if(this.employee == employee) return;

        if(this.employee == null && employee != null && this.stage == Stage.SHIPPING) {
            this.employee = employee;
            employee.addOrder(this);
            return;
        }

        if(this.employee != null && employee == null && this.stage == Stage.CLOSED) {
            Employee tmp = this.employee;
            this.employee = null;
            tmp.removeOrder(this);
            return;
        }

        if(this.employee != null && employee != null && this.stage == Stage.SHIPPING) {
            Employee tmp = this.employee;
            this.employee = null;
            tmp.removeOrder(this);

            setEmployee(employee);
        }
    }

    public void removeEmployee() throws InvalidFieldException {
        setEmployee(null);
    }
}
