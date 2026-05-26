package ecommerce.ecommerce_project.orderClass;

import ecommerce.ecommerce_project.ProductClass.Product;

public  record OrderItem(
        Long orderItemId,
        int quantity,
        String productName
) {
}
