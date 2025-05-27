package DataAccess.Item;

import Exceptions.Item.GetAllItemsException;
import Exceptions.Item.UpdateItemException;
import Exceptions.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.Search.SearchItemException;
import Model.Item.Item;
import Model.Vat.Vat;

import java.util.ArrayList;

public interface ItemDataAccess
{
    ArrayList<Item> getAllItems() throws GetAllItemsException;
    int[] getMinMaxItemQuantityAndPrice(Vat vat) throws GetMinMaxItemQuantityAndPriceException;
    ArrayList<Item> searchItem(String tvaCode, int minItem, int maxItem, int minPrice, int maxPrice)  throws SearchItemException;

    void createItem(Item item);
    void updateItem(Item item) throws UpdateItemException;
    void deleteItem(int id);
    Item getItem(int id);
}
