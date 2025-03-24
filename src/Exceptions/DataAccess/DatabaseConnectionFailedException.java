package Exceptions.DataAccess;

public class DatabaseConnectionFailedException extends Exception
{
    private final String message;
    public DatabaseConnectionFailedException(String message)
    {
        this.message = message;
    }

    public DatabaseConnectionFailedException()
    {
        this("Error while connecting to the database.");
    }

    public String getMessage()
    {
        return this.message;
    }
}
