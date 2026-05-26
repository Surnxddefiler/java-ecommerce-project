package ecommerce.ecommerce_project.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;
    //in which order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity orderEntity;
    //products referred to
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntity;
    //quantity
    @Column(name = "quantity", nullable = false)
    int quantity;
    //price bought at
    @Column(name = "price_bought_at")
    private Double priceBoughtAt;

    public OrderItemEntity(Long orderItemId, OrderEntity orderEntity, ProductEntity productEntity, int quantity, Double priceBoughtAt) {
        this.orderItemId = orderItemId;
        this.orderEntity = orderEntity;
        this.productEntity = productEntity;
        this.quantity = quantity;
        this.priceBoughtAt = priceBoughtAt;
    }

    public OrderItemEntity() {
    }


    //getters
    public Long getOrderItemId() {
        return orderItemId;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public int getQuantity() {
        return quantity;
    }


    @Override
    public String toString() {
        return "OrderItemEntity{" +
                "orderItemId=" + orderItemId +
                ", orderEntity=" + orderEntity +
                ", productEntity=" + productEntity +
                ", quantity=" + quantity +
                '}';
    }
}
