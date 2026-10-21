package ecommerce.ecommerce_project.orderClass;

import jakarta.validation.constraints.NotNull;

public record OrderStatusChange(
        @NotNull
        OrderStatus orderStatus
) {

}
