package ecommerce.ecommerce_project.ProductClass;

public record Product(
        Long id,
        String name,
        double price,
        int stock
) {
}
