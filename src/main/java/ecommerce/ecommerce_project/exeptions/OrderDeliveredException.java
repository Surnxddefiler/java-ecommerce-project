package ecommerce.ecommerce_project.exeptions;

import ecommerce.ecommerce_project.orderClass.OrderStatus;

public class OrderDeliveredException extends RuntimeException {
    public OrderDeliveredException(OrderStatus orderStatus) {
        super("Order is already "+ orderStatus.toString().toLowerCase());
    }
}
