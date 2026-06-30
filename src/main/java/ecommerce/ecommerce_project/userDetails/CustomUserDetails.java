package ecommerce.ecommerce_project.userDetails;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


//implementing custom user details for recieving custom info for user(for example user id)
public class CustomUserDetails implements UserDetails {
    private final Long userId;

    public CustomUserDetails(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
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
