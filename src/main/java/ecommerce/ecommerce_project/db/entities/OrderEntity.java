package ecommerce.ecommerce_project.db.entities;

import ecommerce.ecommerce_project.orderClass.OrderStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", unique = true, nullable = false)
    private Long orderId;

    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    // order products
    @OneToMany(mappedBy = "orderEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    @Column(name = "order_item_id")
    private List<OrderItemEntity> orderItemEntities;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;
    public OrderEntity() {
    }

    public OrderEntity(Long orderId, OrderStatus orderStatus, List<OrderItemEntity> orderItemEntities, UserEntity userEntity) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderItemEntities = orderItemEntities;
        this.userEntity = userEntity;
    }

    //getters

    public Long getOrderId() {
        return orderId;
    }

    public List<OrderItemEntity> getOrderItemEntities() {
        return orderItemEntities;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }


    //setters

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderItemEntities(List<OrderItemEntity> orderItemEntities) {
        this.orderItemEntities = orderItemEntities;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId=" + orderId +
                ", orderStatus=" + orderStatus +
                ", orderItemEntities=" + orderItemEntities.toString() +
                '}';
    }
}
