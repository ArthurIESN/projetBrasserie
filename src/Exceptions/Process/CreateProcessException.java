package Exceptions.Process;

public class CreateProcessException extends Exception
{
    private final String message;

    public CreateProcessException(String message)
    {
        this.message = message;
    }

    public CreateProcessException()
    {
        this("Error while creating the process.");
    }

    public String getMessage()
    {
        return this.message;
    }
}
