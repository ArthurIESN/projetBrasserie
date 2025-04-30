package Exceptions.Access;

public class UnauthorizedAccessException  extends Exception
{
    public UnauthorizedAccessException(String message)
    {
        super(message);
    }

    public UnauthorizedAccessException()
    {
        super("Unauthorized access");
    }
}
