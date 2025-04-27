package DataAccess.Search.SearchItem;

import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.Search.SearchItemException;

import Model.Item.Item;
import Model.Vat.Vat;

public interface SearchItemDataAccess
{
    int[] getMinMaxItemQuantityAndPrice(Vat vat) throws DatabaseConnectionFailedException, GetMinMaxItemQuantityAndPriceException;
    ArrayList<Item> searchItem(String tvaCode, int minItem, int maxItem, int minPrice, int maxPrice)  throws DatabaseConnectionFailedException, SearchItemException;
}
