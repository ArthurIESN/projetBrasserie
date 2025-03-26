package Exceptions.Supplier;

public class GetAllSuppliersException extends Exception
{
    private final String message;

    public GetAllSuppliersException()
    {
        this.message = "An error occurred while trying to get all suppliers.";
    }

    public String getMessage()
    {
        return this.message;
    }
}
