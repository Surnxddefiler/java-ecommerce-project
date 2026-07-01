package ecommerce.ecommerce_project.userClass;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserPasswordRequest(
        @NotBlank
        String oldPassword,
        @NotBlank
        String newPassword
) {

}
