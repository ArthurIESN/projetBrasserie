package Exceptions.Vat;

public class WrongVatCodeException extends Exception {
    private final String message;
    private final String vatCode;

    public WrongVatCodeException(String tvaCode)
    {
        this.vatCode = tvaCode;
        this.message = "The VAT code is not valid. format: VATx, VATxx or VATx.x";
    }

    @Override
    public String getMessage()
    {
        return this.message;
    }

    public String getTvaCode()
    {
        return this.vatCode;
    }
}
