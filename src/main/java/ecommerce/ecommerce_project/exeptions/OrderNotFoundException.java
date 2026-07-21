package ecommerce.ecommerce_project.exeptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("No orders found");
    }
}
