package ecommerce.ecommerce_project.exeptions;

public class WrondPasswordException extends RuntimeException {
    public WrondPasswordException() {
        super("password is incorrect, please try again");
    }
}
