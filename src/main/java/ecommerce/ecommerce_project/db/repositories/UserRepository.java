package ecommerce.ecommerce_project.db.repositories;

import ecommerce.ecommerce_project.db.entities.UserEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUserId(Long userId);
    Optional<UserEntity> findByUserId(Long userId);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
SELECT u FROM UserEntity u
WHERE u.userId=:id
""")
    Optional<UserEntity> findByIdForUpdate(@Param("id") Long id);

    boolean existsByEmail(String email);

    //for userDetails
    Optional<UserEntity> findByEmail(String email);
}
