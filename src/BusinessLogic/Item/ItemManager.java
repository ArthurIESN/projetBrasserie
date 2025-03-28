package BusinessLogic.Item;

import DataAccess.Item.ItemDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Model.Item.Item;

import java.util.List;

public class ItemManager {
    private final ItemDBAccess itemDBAccess = new ItemDBAccess();
    public ItemManager(){}

    public List<Item> getAllItems() throws DatabaseConnectionFailedException
    {
        return itemDBAccess.getAllItems();
    }
}
