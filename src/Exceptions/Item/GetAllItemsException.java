package Exceptions.Item;

public class GetAllItemsException extends Exception
{
    public GetAllItemsException()
    {
        super("Error while getting all items");
    }
}
