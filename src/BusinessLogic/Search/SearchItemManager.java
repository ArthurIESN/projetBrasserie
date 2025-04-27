package BusinessLogic.Search;

import DataAccess.Search.SearchItem.SearchItemDBAccess;
import DataAccess.Search.SearchItem.SearchItemDataAccess;
import DataAccess.Vat.VatDBAccess;
import DataAccess.Vat.VatDataAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.Search.SearchItemException;
import Exceptions.Vat.UnkownVatCodeException;
import Exceptions.Vat.WrongVatCodeException;
import Model.Item.Item;
import Model.Vat.Vat;

import java.util.ArrayList;

public class SearchItemManager
{

    private final SearchItemDataAccess searchItemDataAccess;
    private final VatDataAccess vatDataAccess;

    public SearchItemManager()
    {
        searchItemDataAccess = new SearchItemDBAccess();
        vatDataAccess = new VatDBAccess();
    }

    public ArrayList<Item> searchItem(String vatCode, int minItem, int maxItem, int minPrice, int maxPrice) throws DatabaseConnectionFailedException, WrongVatCodeException, UnkownVatCodeException, SearchItemException
    {
        ArrayList<Item> Items;

        // TVA CODE IS LIKE THIS : TVAX OR TVAXX or TVAX.X
        if(!vatCode.matches("VAT[0-9]{1,2}(\\.[0-9])?"))
        {
            throw new WrongVatCodeException(vatCode);
        }
        else
        {
            Items = searchItemDataAccess.searchItem(vatCode, minItem, maxItem, minPrice, maxPrice);

            // Get the vat
            // Vat code will be the same for each item
            Vat vat = vatDataAccess.getVat(vatCode);

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

    public int[] getMinMaxItemQuantityAndPrice(Vat vat) throws DatabaseConnectionFailedException, GetMinMaxItemQuantityAndPriceException
    {
        return searchItemDataAccess.getMinMaxItemQuantityAndPrice(vat);
    }

}
