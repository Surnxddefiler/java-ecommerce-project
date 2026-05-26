package ecommerce.ecommerce_project.exeptions;

public class QuantityException extends RuntimeException {
    public QuantityException() {
        super("not enough quantity in storage");
    }
}
