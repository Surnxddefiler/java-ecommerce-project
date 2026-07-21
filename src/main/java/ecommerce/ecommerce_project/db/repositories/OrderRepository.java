package ecommerce.ecommerce_project.db.repositories;

import ecommerce.ecommerce_project.db.entities.OrderEntity;
import ecommerce.ecommerce_project.db.entities.OrderItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    //for getting history
    Optional<Page<OrderEntity>> findByUserEntityUserId(Long userId, Pageable pageable);
}
