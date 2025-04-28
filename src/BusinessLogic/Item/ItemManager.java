package BusinessLogic.Item;

import DataAccess.Item.ItemDBAccess;
import DataAccess.Item.ItemDataAccess;
import Exceptions.Item.GetAllItemsException;
import Model.Item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemManager
{
    private final ItemDataAccess itemDBAccess = new ItemDBAccess();
    public ItemManager(){}

    public ArrayList<Item> getAllItems() throws GetAllItemsException
    {
        return itemDBAccess.getAllItems();
    }
}
