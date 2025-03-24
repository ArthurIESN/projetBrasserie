package BusinessLogic.Search;

import DataAccess.Search.SearchItemDBAccess;
import DataAccess.Vat.VatDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DataAccess.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.DataAccess.Search.SearchItemException;
import Exceptions.DataAccess.Vat.UnkownVatCodeException;
import Exceptions.Vat.WrongVatCodeException;
import Model.Item.Item;
import Model.Vat;

import java.util.ArrayList;

public class SearchItemManager
{
    private final SearchItemDBAccess searchItemDBAccess;
    private final VatDBAccess vatDBAccess;

    public SearchItemManager()
    {
        searchItemDBAccess = new SearchItemDBAccess();
        vatDBAccess = new VatDBAccess();
    }

    public ArrayList<Item> searchItem(String tvaCode, int minItem, int maxItem, int minPrice, int maxPrice) throws DatabaseConnectionFailedException, WrongVatCodeException, UnkownVatCodeException, SearchItemException
    {
        ArrayList<Item> Items;

        // @todo : change this to real vat code
        if(!tvaCode.matches("^\\d{1,2}%$"))
        {
            throw new WrongVatCodeException(tvaCode);
        }
        else
        {
            Items = searchItemDBAccess.searchItem(tvaCode, minItem, maxItem, minPrice, maxPrice);

            // Get the vat
            // Vat code will be the same for each item
            Vat vat = vatDBAccess.getVat(tvaCode);

            if(vat != null)
            {
                for(Item item : Items)
                {
                    item.setVat(vat);
                }
            }
        }

        return Items;
    }

    public int[] getMinMaxItemQuantityAndPrice() throws DatabaseConnectionFailedException, GetMinMaxItemQuantityAndPriceException
    {
        return searchItemDBAccess.getMinMaxItemQuantityAndPrice();
    }
}
