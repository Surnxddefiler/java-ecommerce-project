package ecommerce.ecommerce_project.cartClass;

import java.util.List;

//using this class to output cart
public record CartResponse(
        double cartTotalPrice,
        List<CartItem> cartItem
) {

}
