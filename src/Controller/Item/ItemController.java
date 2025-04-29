package Controller.Item;

import BusinessLogic.Item.ItemManager;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Item.GetAllItemsException;
import Exceptions.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.Search.SearchItemException;
import Exceptions.Vat.UnkownVatCodeException;
import Exceptions.Vat.WrongVatCodeException;
import Model.Item.Item;
import Model.Vat.Vat;

import java.util.ArrayList;
import java.util.List;

public class ItemController {
    private static final ItemManager itemManager = new ItemManager();

    public static ArrayList<Item> getAllItems() throws GetAllItemsException
    {
        return itemManager.getAllItems();
    }

    public static ArrayList<Item> searchItem(String tvaCode, int minItem, int maxItem, int minPrice, int maxPrice) throws DatabaseConnectionFailedException, UnkownVatCodeException, SearchItemException, WrongVatCodeException {
        return itemManager.searchItem(tvaCode, minItem, maxItem, minPrice, maxPrice);
    }

    public static int[] getMinMaxItemQuantityAndPrice(Vat vat) throws DatabaseConnectionFailedException, GetMinMaxItemQuantityAndPriceException
    {
        return itemManager.getMinMaxItemQuantityAndPrice(vat);
    }

    public static void createItem(Item item) {
        itemManager.createItem(item);
    }

    public static void updateItem(Item item) {
        itemManager.updateItem(item);
    }

    public static void deleteItem(int id) {
        itemManager.deleteItem(id);
    }

    public static Item getItem(int id) {
        return itemManager.getItem(id);
    }
}
