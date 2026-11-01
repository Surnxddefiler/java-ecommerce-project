package ecommerce.ecommerce_project.exeptions;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(Long cartItemId) {
        super("cart item with id: "+cartItemId+ " not found");
    }
}
