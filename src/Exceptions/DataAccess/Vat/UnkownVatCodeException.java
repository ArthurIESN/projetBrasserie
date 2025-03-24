package Exceptions.DataAccess.Vat;

public class UnkownVatCodeException extends  Exception
{
    private final String message;

    public UnkownVatCodeException(String tvaCode)
    {
        this.message = "Unknown vat code: " + tvaCode + ".";
    }

    public String getMessage()
    {
        return this.message;
    }
}
