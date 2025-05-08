package Exceptions.Payment;

public class GetAllPaymentException extends RuntimeException {
    public GetAllPaymentException()
    {
        super("Error while getting all payments");
    }
}
