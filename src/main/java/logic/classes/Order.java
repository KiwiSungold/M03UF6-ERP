package logic.classes;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Ztudo
 */
public class Order {

    private int orderNumber;
    private Timestamp orderDate;
    private Timestamp requiredDate;
    private Timestamp shippedDate;
    private Customer customer;
    private ArrayList<OrderDetails> orderDetailsList;

    public Order(int orderNumber, Timestamp orderDate, Timestamp requiredDate, Timestamp shippedDate, Customer customer, ArrayList<OrderDetails> orderDetailsList) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.customer = customer;
        this.orderDetailsList = orderDetailsList;
    }

    public Order() {
        orderDetailsList = new ArrayList<>();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Timestamp requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Timestamp getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Timestamp shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(ArrayList<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    @Override
    public String toString() {
        return "Order{" + "orderNumber=" + orderNumber + ", dateTime=" + orderDate + ", requiredDate=" + requiredDate + ", shippedDate=" + shippedDate + ", customer=" + customer + '}';
    }
}
