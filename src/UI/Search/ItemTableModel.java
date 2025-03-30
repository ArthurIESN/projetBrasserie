package UI.Search;

import Model.Item.Item;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ItemTableModel extends AbstractTableModel
{

    private final ArrayList<Item> items;
    private final String[] columnNames = {"ID", "Label", "Price", "Current Quantity", "Restock Quantity", "Packaging ID", "Packaging Label", "Packaging Item Quantity", "VAT Code", "VAT Rate"};

    public ItemTableModel(ArrayList<Item> items)
    {
        this.items = items;
    }
    public ItemTableModel()
    {
        this.items = new ArrayList<>();
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

    public Class getColumnClass(int column)
    {
        Class c;

        switch (column)
        {
            case 0, 3, 4, 5, 7 -> c = Integer.class;
            case 2, 6, 8, 9 -> c = String.class;
            default -> c = String.class;
        }

        return c;
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
            case 7 -> item.getPackaging().getQuantity();
            case 8 -> item.getCode_vat().getCode();
            case 9 -> item.getCode_vat().getRate();
            default -> null;
        };
    }
}
