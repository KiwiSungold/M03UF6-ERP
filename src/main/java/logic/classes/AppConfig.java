/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.classes;

/**
 *
 * @author Kiwi
 */
public class AppConfig {

    private double defaultCreditLimit;
    private int defaultQuantityInStock;
    private int defaultQuantityOrdered;
    private int defaultProductBenefit;
    private int minShippingHours;
    private int minCustomerAge;
    private int maxLinesPerOrder;
    private double maxOrderAmount;

    public AppConfig(double defaultCreditLimit, int defaultQuantityInStock, int defaultQuantityOrdered, int defaultProductBenefit, int minShippingHours, int minCustomerAge, int maxLinesPerOrder, double maxOrderAmount) {
        this.defaultCreditLimit = defaultCreditLimit;
        this.defaultQuantityInStock = defaultQuantityInStock;
        this.defaultQuantityOrdered = defaultQuantityOrdered;
        this.defaultProductBenefit = defaultProductBenefit;
        this.minShippingHours = minShippingHours;
        this.minCustomerAge = minCustomerAge;
        this.maxLinesPerOrder = maxLinesPerOrder;
        this.maxOrderAmount = maxOrderAmount;
    }

    public AppConfig() {
    }

    public double getDefaultCreditLimit() {
        return defaultCreditLimit;
    }

    public void setDefaultCreditLimit(double defaultCreditLimit) {
        this.defaultCreditLimit = defaultCreditLimit;
    }

    public int getDefaultQuantityInStock() {
        return defaultQuantityInStock;
    }

    public void setDefaultQuantityInStock(int defaultQuantityInStock) {
        this.defaultQuantityInStock = defaultQuantityInStock;
    }

    public int getDefaultQuantityOrdered() {
        return defaultQuantityOrdered;
    }

    public void setDefaultQuantityOrdered(int defaultQuantityOrdered) {
        this.defaultQuantityOrdered = defaultQuantityOrdered;
    }

    public int getDefaultProductBenefit() {
        return defaultProductBenefit;
    }

    public void setDefaultProductBenefit(int defaultProductBenefit) {
        this.defaultProductBenefit = defaultProductBenefit;
    }

    public int getMinShippingHours() {
        return minShippingHours;
    }

    public void setMinShippingHours(int minShippingHours) {
        this.minShippingHours = minShippingHours;
    }

    public int getMinCustomerAge() {
        return minCustomerAge;
    }

    public void setMinCustomerAge(int minCustomerAge) {
        this.minCustomerAge = minCustomerAge;
    }

    public int getMaxLinesPerOrder() {
        return maxLinesPerOrder;
    }

    public void setMaxLinesPerOrder(int maxLinesPerOrder) {
        this.maxLinesPerOrder = maxLinesPerOrder;
    }

    public double getMaxOrderAmount() {
        return maxOrderAmount;
    }

    public void setMaxOrderAmount(double maxOrderAmount) {
        this.maxOrderAmount = maxOrderAmount;
    }

    @Override
    public String toString() {
        return "AppConfig{" + "defaultCreditLimit=" + defaultCreditLimit + ", defaultQuantityInStock=" + defaultQuantityInStock + ", defaultQuantityOrdered=" + defaultQuantityOrdered + ", defaultProductBenefit=" + defaultProductBenefit + ", minShippingHours=" + minShippingHours + ", minCustomerAge=" + minCustomerAge + ", maxLinesPerOrder=" + maxLinesPerOrder + ", maxOrderAmount=" + maxOrderAmount + '}';
    }
}
