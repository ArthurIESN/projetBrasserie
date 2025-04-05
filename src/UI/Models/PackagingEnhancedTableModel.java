package UI.Models;

import Model.Packaging.Packaging;
import UI.Components.AbstractEnhancedTableModel;

import java.util.ArrayList;

public class PackagingEnhancedTableModel extends AbstractEnhancedTableModel<Packaging>
{
    public PackagingEnhancedTableModel(ArrayList<Packaging> data)
    {
        super("Packaging", new String[]{"ID", "Label", "Quantity"}, data);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Packaging packaging = getData().get(rowIndex);

        if (packaging == null)
        {
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> packaging.getId();
            case 1 -> packaging.getLabel();
            case 2 -> packaging.getQuantity();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return switch (columnIndex) {
            case 0, 2 -> Integer.class;
            default -> String.class;
        };
    }
}
