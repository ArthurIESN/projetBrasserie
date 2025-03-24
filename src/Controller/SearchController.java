package Controller;

import BusinessLogic.Search.SearchItemManager;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DataAccess.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.tva.WrongTvaCodeException;
import Model.Item.Item;

import java.util.ArrayList;

public class SearchController
{
    private final SearchItemManager searchItemManager;

    public SearchController()
    {
        searchItemManager = new SearchItemManager();
    }

    public ArrayList<Item> searchItem(String tvaCode, int minItem, int maxItem, int minPrice, int maxPrice) throws DatabaseConnectionFailedException, WrongTvaCodeException
    {
        return searchItemManager.searchItem(tvaCode, minItem, maxItem, minPrice, maxPrice);
    }

    public int[] getMinMaxItemQuantityAndPrice() throws DatabaseConnectionFailedException, GetMinMaxItemQuantityAndPriceException
    {
        return searchItemManager.getMinMaxItemQuantityAndPrice();
    }
}
