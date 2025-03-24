package Controller;

import BusinessLogic.Item.ItemManager;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Model.Item.Item;

import java.util.List;

public class AppController {
    private static final AppController instance = new AppController();
    private final ItemManager itemManager;
    public AppController(){
        itemManager = new ItemManager();
    }

    public static AppController getInstance(){
        return instance;
    }

    public List<Item> getAllItems() throws DatabaseConnectionFailedException{
        return itemManager.getAllItems();
    }
}
