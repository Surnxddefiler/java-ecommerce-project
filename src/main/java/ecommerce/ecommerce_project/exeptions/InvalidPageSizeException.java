package ecommerce.ecommerce_project.exeptions;

public class InvalidPageSizeException extends RuntimeException {
    public InvalidPageSizeException(int pageSize) {
        super("Invalid page size, page size should be more then 1 and less then 50, your page size: " + pageSize);
    }
}
