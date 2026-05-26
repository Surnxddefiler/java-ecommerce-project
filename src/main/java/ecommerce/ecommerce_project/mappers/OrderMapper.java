package ecommerce.ecommerce_project.mappers;

import ecommerce.ecommerce_project.ProductClass.Product;
import ecommerce.ecommerce_project.db.entities.OrderEntity;
import ecommerce.ecommerce_project.db.entities.OrderItemEntity;
import ecommerce.ecommerce_project.db.entities.ProductEntity;
import ecommerce.ecommerce_project.orderClass.Order;
import ecommerce.ecommerce_project.orderClass.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {


    //to order item(for to order method)
    private List<OrderItem> orderItemList(List<OrderItemEntity> orderItemEntities){
        List<OrderItem> orderItems=new ArrayList<>();
        orderItemEntities.forEach(orderItemEntity -> {
            orderItems.add(new OrderItem(orderItemEntity.getOrderItemId(), orderItemEntity.getQuantity(), orderItemEntity.getProductEntity().getName()));
        });
        return orderItems;
    }

    public Order toOrder(OrderEntity orderEntity){
        return new Order(orderEntity.getOrderId(),orderItemList(orderEntity.getOrderItemEntities()),orderEntity.getOrderStatus());
    }
}
