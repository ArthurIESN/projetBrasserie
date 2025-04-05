package UI.Models;

import Model.Item.Item;
import UI.Components.EnhancedTable.AbstractEnhancedTableModel;

import java.util.ArrayList;

public class ItemEnhancedTableModel extends AbstractEnhancedTableModel<Item>
{
    public ItemEnhancedTableModel(ArrayList<Item> data)
    {
        super("Item", new String[]{"ID", "Label", "Price", "Restock Quantity", "Current Quantity", "Empty Returnable Bottle Quantity", "Empty Returnable Bottle Price", "Forecast Date", "Forecast Quantity", "Min Quantity"}, data);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Item item = getData().get(rowIndex);

        if (item == null)
        {
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> item.getId();
            case 1 -> item.getLabel();
            case 2 -> item.getPrice();
            case 3 -> item.getRestockQuantity();
            case 4 -> item.getCurrentQuantity();
            case 5 -> item.getEmptyReturnableBottleQuantity();
            case 6 -> item.getEmptyReturnableBottlePrice();
            case 7 -> item.getForecastDate();
            case 8 -> item.getForecastQuantity();
            case 9 -> item.getMinQuantity();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return switch (columnIndex) {
            case 0, 2, 3, 4, 5, 6, 8, 9 -> Integer.class;
            case 7 -> java.util.Date.class;
            default -> String.class;
        };
    }
}
