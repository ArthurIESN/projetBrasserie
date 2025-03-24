package Exceptions.DataAccess;

public class DatabaseConnectionFailedException extends Exception
{
    private final String message;
    public DatabaseConnectionFailedException(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return this.message;
    }
}
