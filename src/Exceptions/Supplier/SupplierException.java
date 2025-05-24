package Exceptions.Supplier;

public class SupplierException extends Exception{
    private final String message;

    public SupplierException(String message) {
      this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
