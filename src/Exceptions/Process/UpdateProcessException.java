package Exceptions.Process;

public class UpdateProcessException extends Exception
{
    private final String message;
    public UpdateProcessException(String message)
    {
        this.message = message;
    }

    public UpdateProcessException()
    {
        this("Error while updating the process.");
    }

    public String getMessage()
    {
        return this.message;
    }
}
