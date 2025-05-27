package Exceptions.Process;

public class GetAllProcessesException extends Exception
{
    private final String message;

    public GetAllProcessesException()
    {
        this.message = "Error while getting all processes.";
    }

    @Override
    public String getMessage()
    {
        return this.message;
    }
}
