package ecommerce.ecommerce_project.exeptions;

public class BalanceException extends RuntimeException {
    public BalanceException() {
        super("Not enough money on balance");
    }
}
