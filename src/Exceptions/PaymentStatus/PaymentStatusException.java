package Exceptions.PaymentStatus;

public class PaymentStatusException extends RuntimeException {
    private String message;
    public PaymentStatusException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
