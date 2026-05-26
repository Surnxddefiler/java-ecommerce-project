package ecommerce.ecommerce_project.orderClass;

import ecommerce.ecommerce_project.cartClass.CartItem;
import ecommerce.ecommerce_project.db.entities.OrderItemEntity;

import java.util.List;

public record Order(
        Long orderId,
        List<OrderItem> orderItems,
        OrderStatus orderStatus
) {
}
