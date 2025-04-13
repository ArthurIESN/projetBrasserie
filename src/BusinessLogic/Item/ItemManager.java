package BusinessLogic.Item;

import DataAccess.Item.ItemDBAccess;
import Exceptions.Item.GetAllItemsException;
import Model.Item.Item;

import java.util.List;

public class ItemManager {
    private final ItemDBAccess itemDBAccess = new ItemDBAccess();
    public ItemManager(){}

    public List<Item> getAllItems() throws GetAllItemsException
    {
        return itemDBAccess.getAllItems();
    }
}
