package BusinessLogic;

import DataAccess.SearchItemDBAccess;
import Model.Item.Item;

import java.util.ArrayList;

public class SearchItemManager
{
    private final SearchItemDBAccess searchItemDBAccess;

    public SearchItemManager()
    {
        searchItemDBAccess = new SearchItemDBAccess();
    }

    public ArrayList<Item> searchItem(String tvaCode, int minItem, int maxItem, int minPrice, int maxPrice)
    {
        if(!tvaCode.matches("[0-9%]+"))
        {
            // @todo : throw exception
        }

        return searchItemDBAccess.searchItem(tvaCode, minItem, maxItem, minPrice, maxPrice);
    }

    public int[] getMinMaxItemQuantityAndPrice()
    {
        return searchItemDBAccess.getMinMaxItemQuantityAndPrice();
    }
}

// Je fais un test