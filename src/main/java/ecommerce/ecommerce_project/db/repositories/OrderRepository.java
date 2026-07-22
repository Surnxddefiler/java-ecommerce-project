package ecommerce.ecommerce_project.db.repositories;

import ecommerce.ecommerce_project.db.entities.OrderEntity;
import ecommerce.ecommerce_project.db.entities.OrderItemEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    //for getting history
    Optional<Page<OrderEntity>> findByUserEntityUserId(Long userId, Pageable pageable);

//    locking
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
SELECT o FROM OrderEntity o
WHERE o.orderId=:orderId
AND o.userEntity.userId=:userId
""")
    Optional<OrderEntity> findByOrderIdForUpdate(Long orderId, Long userId);

}
