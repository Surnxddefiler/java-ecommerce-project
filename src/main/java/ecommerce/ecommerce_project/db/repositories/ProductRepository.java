package ecommerce.ecommerce_project.db.repositories;


import ecommerce.ecommerce_project.db.entities.ProductEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    //single product
    Optional<ProductEntity> findByProductId(Long productId);
    //multipleproduct
    @Query("""
            SELECT p FROM ProductEntity p
            WHERE
            (:startPrice IS NULL OR p.price>= :startPrice)
            AND(:endPrice IS NULL OR p.price<= :endPrice)
            """)
    public Page<ProductEntity> getByFilters(@Param("startPrice") Double startPrice, @Param("endPrice") Double endPrice, Pageable pageable);

    @Modifying
    @Query("""
UPDATE ProductEntity p
SET p.stock=p.stock-:quantity
WHERE p.productId=:productId
""")
    void decrementStock(Long productId, int quantity);

    //creating pessimistic locking for race condition
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
SELECT p FROM ProductEntity p
where p.productId=:id
""")
    Optional<ProductEntity> findByIdForUpdate(@Param("id") Long id);
}
