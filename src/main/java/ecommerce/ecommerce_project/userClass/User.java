package ecommerce.ecommerce_project.userClass;

public record User(
        Long userId,
        String username,
        String email,
        String password,
        Double balance
) {
}
