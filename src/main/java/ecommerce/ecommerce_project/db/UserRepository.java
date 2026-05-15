package ecommerce.ecommerce_project.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUserId(Long userId);
    Optional<UserEntity> findByUserId(Long userId);
}
