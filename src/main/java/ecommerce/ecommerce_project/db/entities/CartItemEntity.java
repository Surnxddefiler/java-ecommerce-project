package ecommerce.ecommerce_project.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_item",
        //dealing with race condition
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_id", "product_id"}
                )
        }
)
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long cartItemId;
    //quantity of product
    @Column(name = "quantity")
    private int quantity;
    //product itself
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
    //which cart related to
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;
    //optimistic locking version
    //constructors
    public CartItemEntity() {
    }

    public CartItemEntity(Long cartItemId, int quantity, ProductEntity productEntity, UserEntity userEntity) {
        this.cartItemId = cartItemId;
        this.quantity = quantity;
        this.productEntity = productEntity;
        this.userEntity=userEntity;
    }
    //setters

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //getters
    public int getQuantity() {
        return quantity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }
}
