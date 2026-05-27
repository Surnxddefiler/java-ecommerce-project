package ecommerce.ecommerce_project.exeptions;

public class UsernameException extends RuntimeException {
    public UsernameException(String username) {
        super("User with username: "+username+" already exists");
    }
}
