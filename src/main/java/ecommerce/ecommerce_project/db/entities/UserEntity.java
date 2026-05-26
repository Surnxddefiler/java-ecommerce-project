package ecommerce.ecommerce_project.db.entities;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

@Entity
@Table(name = "users")
public class UserEntity {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long userId;
    @Column(name = "username", nullable = false)
    String username;
    @Column(name = "email", unique = true, nullable = false)
    String email;
    @Column(name = "password", unique = true, nullable = false)
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

//    setters

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
