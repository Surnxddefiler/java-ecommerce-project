package ecommerce.ecommerce_project.userDetails;

import ecommerce.ecommerce_project.userClass.UserRole;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


//implementing custom user details for recieving custom info for user(for example user id)
public class CustomUserDetails implements UserDetails {
    private final Long userId;
    private final UserRole userRole;

    public CustomUserDetails(Long userId, UserRole userRole) {
        this.userId = userId;
        this.userRole=userRole;
    }

    public Long getUserId() {
        return userId;
    }

    //admin role
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.toString()));
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return userId.toString();
    }
}
