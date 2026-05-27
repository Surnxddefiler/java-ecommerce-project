package ecommerce.ecommerce_project.userClass;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotNull
        String username,
        @NotNull
        @Email
        String email,
        @NotNull
        String password
) {
}
