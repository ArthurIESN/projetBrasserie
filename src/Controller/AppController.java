package Controller;

import BusinessLogic.Item.ItemManager;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Model.Item.Item;

import java.util.List;

public class AppController {
    private static final ItemManager itemManager = new ItemManager();

    public static List<Item> getAllItems() throws DatabaseConnectionFailedException
    {
        return itemManager.getAllItems();
    }
}
