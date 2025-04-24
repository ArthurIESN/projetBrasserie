package Controller.Item;

import BusinessLogic.Item.ItemManager;
import Exceptions.Item.GetAllItemsException;
import Model.Item.Item;

import java.util.List;

public class ItemController {
    private static final ItemManager itemManager = new ItemManager();

    public static List<Item> getAllItems() throws GetAllItemsException
    {
        return itemManager.getAllItems();
    }
}
