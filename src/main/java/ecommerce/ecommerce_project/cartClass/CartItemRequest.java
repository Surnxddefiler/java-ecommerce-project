package ecommerce.ecommerce_project.cartClass;

import jakarta.validation.constraints.NotNull;

public record CartItemRequest(
        @NotNull
        int quantity,
        @NotNull
        Long productId
) {
}
