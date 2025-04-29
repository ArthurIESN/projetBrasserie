package BusinessLogic.Item;

import DataAccess.Item.ItemDBAccess;
import DataAccess.Item.ItemDataAccess;
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

public class ItemManager
{
    private final ItemDataAccess itemDBAccess = new ItemDBAccess();
    public ItemManager(){}

    public ArrayList<Item> getAllItems() throws GetAllItemsException
    {
        return itemDBAccess.getAllItems();
    }

    public ArrayList<Item> searchItem(String vatCode, int minItem, int maxItem, int minPrice, int maxPrice) throws WrongVatCodeException, SearchItemException
    {
        ArrayList<Item> Items;

        // TVA CODE IS LIKE THIS : TVAX OR TVAXX or TVAX.X
        if(!vatCode.matches("VAT[0-9]{1,2}(\\.[0-9])?"))
        {
            throw new WrongVatCodeException(vatCode);
        }
        else
        {
            Items = itemDBAccess.searchItem(vatCode, minItem, maxItem, minPrice, maxPrice);
        }

        return Items;
    }

    public int[] getMinMaxItemQuantityAndPrice(Vat vat) throws GetMinMaxItemQuantityAndPriceException
    {
        return itemDBAccess.getMinMaxItemQuantityAndPrice(vat);
    }

    public void createItem(Item item)
    {
        itemDBAccess.createItem(item);
    }

    public void updateItem(Item item)
    {
        itemDBAccess.updateItem(item);
    }

    public void deleteItem(int id)
    {
        itemDBAccess.deleteItem(id);
    }

    public Item getItem(int id)
    {
        return itemDBAccess.getItem(id);
    }
}
