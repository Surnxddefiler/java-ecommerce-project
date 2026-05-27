package ecommerce.ecommerce_project.userClass;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotNull
        @NotBlank
        String username,
        @NotNull
        @Email
        String email,
        @NotNull
        String password
) {
}
