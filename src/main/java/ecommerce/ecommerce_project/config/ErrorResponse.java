package ecommerce.ecommerce_project.config;

import java.time.LocalDateTime;

public record ErrorResponse(
        String title,
        String message,
        LocalDateTime time
) {
}
