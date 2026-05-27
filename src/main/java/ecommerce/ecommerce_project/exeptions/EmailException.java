package ecommerce.ecommerce_project.exeptions;

public class EmailException extends RuntimeException {
    public EmailException(String email) {
        super("User with email: "+email+" already exists");
    }
}
