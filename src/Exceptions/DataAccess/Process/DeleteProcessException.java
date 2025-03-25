package Exceptions.DataAccess.Process;

public class DeleteProcessException extends Exception
{
    private final String message;
    public DeleteProcessException(String message)
    {
        this.message = message;
    }

    public DeleteProcessException()
    {
        this("Error while deleting the process.");
    }

    public String getMessage()
    {
        return this.message;
    }
}
