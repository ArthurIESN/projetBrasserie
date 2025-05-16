package Exceptions.Payment;

public class PaymentException extends RuntimeException {
    private String message;
    public PaymentException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
