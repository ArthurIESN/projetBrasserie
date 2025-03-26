package DataAccess.Search.SearchItem;

import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DataAccess.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.DataAccess.Search.SearchItemException;

import Model.Item.Item;

public interface SearchItemDataAccess
{
    int[] getMinMaxItemQuantityAndPrice() throws DatabaseConnectionFailedException, GetMinMaxItemQuantityAndPriceException;
    ArrayList<Item> searchItem(String tvaCode, int minItem, int maxItem, int minPrice, int maxPrice)  throws DatabaseConnectionFailedException, SearchItemException;
}
