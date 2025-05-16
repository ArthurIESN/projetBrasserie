package Exceptions.Vat;

public class VatException extends Exception {
    private final String message;

    public VatException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
