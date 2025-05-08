package Exceptions.Tasks.RestockItem;

public class RestockQuantityAndDateException extends Exception
{
    public RestockQuantityAndDateException(String message)
    {
        super(message);
    }

    public RestockQuantityAndDateException()
    {
        super("Error in calculating restock quantity and date");
    }

}
