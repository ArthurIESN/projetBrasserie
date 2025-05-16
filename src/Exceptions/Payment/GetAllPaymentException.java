package Exceptions.Payment;

public class GetAllPaymentException extends Exception {
    public GetAllPaymentException()
    {
        super("Error while getting all payments");
    }
}
