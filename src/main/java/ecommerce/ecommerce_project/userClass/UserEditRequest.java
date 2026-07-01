package ecommerce.ecommerce_project.userClass;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserEditRequest(
        @Email
        String email,
        @Size(min = 1)
        String username
) {
}
