package ecommerce.ecommerce_project.ProductClass;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ProductEditRequest(
        @Size(min = 1)
        String name,
        @PositiveOrZero
        Double price,
        @PositiveOrZero
        Integer stock
) {
}
