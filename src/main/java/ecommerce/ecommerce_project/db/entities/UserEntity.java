package ecommerce.ecommerce_project.db.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

@Entity
@Table(name = "users",
uniqueConstraints = {
        @UniqueConstraint(name = "uk_users_email", columnNames = "email"),
        @UniqueConstraint(name = "uk_users_username", columnNames = "username")
}
)
public class UserEntity {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long userId;
    @Column(name = "username", nullable = false)
    String username;
    @Column(name = "email", nullable = false)
    String email;
    @Column(name = "password", nullable = false)
    String password;
    @Column(name = "balance")
    Double balance;
    //constructors

    public UserEntity() {
    }

    public UserEntity(Long userId, String username, String email, String password, Double balance) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }
    //getters

    public Long getUserId() {
        return userId;
    }

    public Double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    //    setters

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
