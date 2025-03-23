package Model.Item;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ItemTable extends AbstractTableModel
{

    private final ArrayList<Item> items;
    private final String[] columnNames = {"ID", "Label", "Price", "Current Quantity", "Restock Quantity", "Packaging ID", "Packaging Label", "Packaging Item Quantity", "VAT Code", "VAT Rate"};

    public ItemTable(ArrayList<Item> items)
    {
        this.items = items;
    }

    @Override
    public int getRowCount()
    {
        return items.size();
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int i)
    {
        return columnNames[i];
    }

    @Override
    public Object getValueAt(int i, int i1)
    {
        Item item = items.get(i);
        return switch (i1)
        {
            case 0 -> item.getId();
            case 1 -> item.getLabel();
            case 2 -> item.getPrice();
            case 3 -> item.getCurrent_quantity();
            case 4 -> item.getRestock_quantity();
            case 5 -> item.getPackaging().getId();
            case 6 -> item.getPackaging().getLabel();
            case 7 -> item.getPackaging().getItem_quantity();
            case 8 -> item.getCode_vat().getCode();
            case 9 -> item.getCode_vat().getRate();
            default -> null;
        };
    }
}
