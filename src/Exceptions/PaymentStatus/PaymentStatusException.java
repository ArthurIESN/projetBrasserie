package Exceptions.PaymentStatus;

public class PaymentStatusException extends RuntimeException {
    private final String message;
    public PaymentStatusException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
