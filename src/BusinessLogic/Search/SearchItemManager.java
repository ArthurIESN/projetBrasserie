package BusinessLogic.Search;

import DataAccess.Search.SearchItemDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DataAccess.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.tva.WrongTvaCodeException;
import Model.Item.Item;

import java.util.ArrayList;

public class SearchItemManager
{
    private final SearchItemDBAccess searchItemDBAccess;

    public SearchItemManager()
    {
        searchItemDBAccess = new SearchItemDBAccess();
    }

    public ArrayList<Item> searchItem(String tvaCode, int minItem, int maxItem, int minPrice, int maxPrice) throws DatabaseConnectionFailedException, WrongTvaCodeException
    {
        // throw an exception when the tvaCode is not x% or xx%
        if(!tvaCode.matches("^\\d{1,2}%$"))
        {
            throw new WrongTvaCodeException(tvaCode);
        }
        else
        {
            return searchItemDBAccess.searchItem(tvaCode, minItem, maxItem, minPrice, maxPrice);
        }
    }

    public int[] getMinMaxItemQuantityAndPrice() throws DatabaseConnectionFailedException, GetMinMaxItemQuantityAndPriceException
    {
        return searchItemDBAccess.getMinMaxItemQuantityAndPrice();
    }
}
