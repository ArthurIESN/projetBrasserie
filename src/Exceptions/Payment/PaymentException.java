package Exceptions.Payment;

public class PaymentException extends RuntimeException {
    private final String message;
    public PaymentException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
