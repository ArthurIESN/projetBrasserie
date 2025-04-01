package Model.Item;

import Model.Packaging.Packaging;
import Model.Vat.Vat;

import java.util.Date;
import java.util.HashMap;

public class MakeItem
{
    private static final HashMap<Integer, Item> itemMap = new HashMap<>();

    public static Item getItem(Integer id, String label, float price, int restock_quantity, int current_quantity, int empty_returnable_bottle_quantity, float empty_returnable_bottle_price, Date forecast_date, int forecast_quantity, int min_quantity, Packaging packaging, Vat vat)
    {
        if(itemMap.containsKey(id))
        {
            return itemMap.get(id);
        }
        else
        {
            Item item = new Item(id, label, price, restock_quantity, current_quantity, empty_returnable_bottle_quantity, empty_returnable_bottle_price, forecast_date, forecast_quantity, min_quantity, packaging, vat);
            itemMap.put(id, item);
            return item;
        }
    }
}
