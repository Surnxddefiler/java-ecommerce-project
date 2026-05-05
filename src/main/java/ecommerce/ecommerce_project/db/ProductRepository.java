package ecommerce.ecommerce_project.db;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("""
            SELECT p FROM ProductEntity p
            WHERE
            (:startPrice IS NULL OR p.price>= :startPrice)
            AND(:endPrice IS NULL OR p.price<= :endPrice)
            """)
    public Page<ProductEntity> getByFilters(@Param("startPrice") Double startPrice, @Param("endPrice") Double endPrice, Pageable pageable);
}
