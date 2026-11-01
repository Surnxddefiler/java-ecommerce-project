package ecommerce.ecommerce_project.cartClass;

import jakarta.validation.constraints.Positive;

//record for delete cart endpoint
public record CartDeleteRequest(
        @Positive
        int quantity
) {
}
