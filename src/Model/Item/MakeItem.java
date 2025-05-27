package Model.Item;

import Exceptions.Item.ItemException;
import Model.Packaging.Packaging;
import Model.Vat.Vat;

import java.util.Date;
import java.util.HashMap;

public class MakeItem
{
    private static final HashMap<Integer, Item> itemMap = new HashMap<>();

    public static Item getItem(Integer id, String label, float price, int restockQuantity, int currentQuantity, int emptyReturnableBottleQuantity, float emptyReturnableBottlePrice, Date forecastDate, int forecastQuantity, int minQuantity, Packaging packaging, Vat vat)
    {
        int itemHash = Item.hashCode(id, label, price, restockQuantity, currentQuantity, emptyReturnableBottleQuantity, emptyReturnableBottlePrice, forecastDate, forecastQuantity, minQuantity, packaging, vat);

        if(itemMap.containsKey(itemHash))
        {
            return itemMap.get(itemHash);
        }
        else
        {
            try
            {
                Item item = new Item(id, label, price, restockQuantity, currentQuantity, emptyReturnableBottleQuantity, emptyReturnableBottlePrice, forecastDate, forecastQuantity, minQuantity, packaging, vat);
                itemMap.put(itemHash, item);
                return item;
            }
            catch (ItemException e)
            {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
