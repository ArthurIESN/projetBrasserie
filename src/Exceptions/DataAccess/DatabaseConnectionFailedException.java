package Exceptions.DataAccess;

public class DatabaseConnectionFailedException extends Exception
{
    public DatabaseConnectionFailedException(String message)
    {
        super(message);
    }
}
