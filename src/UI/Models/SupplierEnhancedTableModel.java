package UI.Models;

import Model.Supplier.Supplier;
import UI.Components.EnhancedTable.AbstractEnhancedTableModel;

import java.util.ArrayList;

public class SupplierEnhancedTableModel extends AbstractEnhancedTableModel<Supplier>
{
    public SupplierEnhancedTableModel(ArrayList<Supplier> data)
    {
        super("Supplier", new String[]{"ID", "Name"}, data);
    }

    public SupplierEnhancedTableModel()
    {
        this(new ArrayList<Supplier>());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Supplier supplier = getData().get(rowIndex);

        if(supplier == null)
        {
            return " - ";
        }

        return switch (columnIndex)
        {
            case 0 -> supplier.getId();
            case 1 -> supplier.getName();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            default -> String.class;
        };
    }
}
