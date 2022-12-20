/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.classes;

/**
 *
 * @author Ztudo
 */
public class Customer {

    private String customerEmail;
    private String idCard;
    private String customerName;
    private String phoneNumber;
    private double creditLimit;
    private String birthDate;

    public Customer(String customerEmail, String idCard, String customerName, String phoneNumber, double creditLimit, String birthDate) {
        this.customerEmail = customerEmail;
        this.idCard = idCard;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.creditLimit = creditLimit;
        this.birthDate = birthDate;
    }

    public Customer() {
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return " " + customerEmail + "";
    }
}
