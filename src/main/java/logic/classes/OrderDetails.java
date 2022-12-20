package logic.classes;

/**
 *
 * @author Ztudo
 */
public class OrderDetails {

    private Product product;
    private int orderId;
    private int quantityOrdered;
    private double priceEach;
    private int orderLineNumber;
    private double orderLineTotal;

    public OrderDetails(Product product, int orderId, int quantityOrdered, double priceEach, int orderLineNumber, double orderLineTotal) {
        this.product = product;
        this.orderId = orderId;
        this.quantityOrdered = quantityOrdered;
        this.priceEach = priceEach;
        this.orderLineNumber = orderLineNumber;
        this.orderLineTotal = orderLineTotal;
    }

    public OrderDetails() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public double getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(double priceEach) {
        this.priceEach = priceEach;
    }

    public int getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setOrderLineNumber(int orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    public double getOrderLineTotal() {
        return orderLineTotal;
    }

    public void setOrderLineTotal(double orderLineTotal) {
        this.orderLineTotal = orderLineTotal;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "product=" + product + ", order=" + orderId + ", quantityOrdered=" + quantityOrdered + ", priceEach=" + priceEach + ", orderLineNumber=" + orderLineNumber + '}';
    }
}
