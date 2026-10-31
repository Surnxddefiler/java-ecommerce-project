package ecommerce.ecommerce_project.ProductClass;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record Product(
        Long id,
        @NotNull
        @NotEmpty
        String name,
        @PositiveOrZero
        double price,
        @PositiveOrZero
        int stock
) {
}
