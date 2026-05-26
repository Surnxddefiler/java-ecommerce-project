package ecommerce.ecommerce_project.db.repositories;

import ecommerce.ecommerce_project.db.entities.OrderEntity;
import ecommerce.ecommerce_project.db.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
