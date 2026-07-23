package ecommerce.ecommerce_project.db.repositories;

import ecommerce.ecommerce_project.db.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

@Query("""
SELECT SUM(oi.quantity*oi.priceBoughtAt) AS sum FROM OrderItemEntity oi
WHERE oi.orderEntity.orderId=:orderId
""")
    Optional<Double> getTotalPrice(Long orderId);
}
