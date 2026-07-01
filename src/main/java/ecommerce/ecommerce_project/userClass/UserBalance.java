package ecommerce.ecommerce_project.userClass;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record UserBalance (
        @Positive
        @Min(value = 5)
        Double money
){
}
