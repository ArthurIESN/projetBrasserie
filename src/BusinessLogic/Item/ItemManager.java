package BusinessLogic.Item;

import DataAccess.Item.DataAccessItem;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Model.Item.Item;

import java.util.List;

public class ItemManager {
    private final DataAccessItem dataAccessItem = new DataAccessItem();
    public ItemManager(){}

    public List<Item> getAllItems() throws DatabaseConnectionFailedException
    {
        return dataAccessItem.getAllItems();
    }
}
