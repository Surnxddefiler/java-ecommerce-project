package ecommerce.ecommerce_project.cartClass;

import ecommerce.ecommerce_project.ProductClass.Product;

public record CartItem(
        Long productId,
        String name,
        int quantity,
        double totalPrice
) {
}
