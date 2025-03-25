package Exceptions.DataAccess.Process;

public class GetProcessException extends Exception
{
    private final String message;
    public GetProcessException(String message)
    {
        this.message = message;
    }

    public GetProcessException()
    {
        this("Error while getting the process.");
    }

    public String getMessage()
    {
        return this.message;
    }
}
