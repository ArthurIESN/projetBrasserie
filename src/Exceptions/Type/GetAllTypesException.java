package Exceptions.Type;

public class GetAllTypesException extends Exception
{
    private final String message;

    public GetAllTypesException()
    {
        this.message = "Error while getting all types.";
    }

    @Override
    public String getMessage()
    {
        return this.message;
    }
}
