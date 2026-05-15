package ecommerce.ecommerce_project.db;

import ecommerce.ecommerce_project.cartClass.CartItem;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    boolean existsByProductEntityProductIdAndUserEntityUserId(Long productId, Long userId);

//    CartItemEntity findByProductEntityProductIdAndUserEntityUserId(Long productId, Long userId);

    //creating query for modifying quantity
    @Modifying
    @Query("""
UPDATE CartItemEntity c
SET c.quantity=c.quantity+:quantity
WHERE c.productEntity.productId=:productId
AND c.userEntity.userId=:userId
""")
    void addQuantity(Long productId, Long userId, @NotNull int quantity);

@Query("""
SELECT
    SUM(ci.quantity * p.price) AS total_cart_price FROM CartItemEntity ci
JOIN productEntity p
    ON ci.productEntity.productId = p.productId
WHERE ci.userEntity.userId = :userId
""")
    double getCartTotalPrice(Long userId);

@Query("""
select new ecommerce.ecommerce_project.cartClass.CartItem(
p.productId, 
p.name, 
ci.quantity, 
ci.quantity*p.price
) from CartItemEntity ci
join productEntity p on ci.productEntity.productId = p.productId
where ci.userEntity.userId =:userId
""")
    List<CartItem> getAllItems(Long userId);
}
