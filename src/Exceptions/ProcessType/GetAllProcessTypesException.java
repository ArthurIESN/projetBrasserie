package Exceptions.ProcessType;

public class GetAllProcessTypesException extends Exception
{
    private final String message;

    public GetAllProcessTypesException()
    {
        this.message = "Error while getting all types.";
    }

    @Override
    public String getMessage()
    {
        return this.message;
    }
}
