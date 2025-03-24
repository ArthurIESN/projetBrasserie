package Exceptions.Vat;

public class WrongVatCodeException extends Exception {
    private final String message;
    private final String tvaCode;

    public WrongVatCodeException(String tvaCode)
    {
        this.tvaCode = tvaCode;
        this.message = "The tva code is not valid. format: x% or xx%.";
    }

    public String getMessage()
    {
        return this.message;
    }

    public String getTvaCode()
    {
        return this.tvaCode;
    }
}
