package UI.Models;

import Model.CustomerStatus.CustomerStatus;
import UI.Components.EnhancedTable.AbstractEnhancedTableModel;

import java.util.ArrayList;

public class CustomerStatusEnhancedTableModel extends AbstractEnhancedTableModel<CustomerStatus>
{
    public CustomerStatusEnhancedTableModel(ArrayList<CustomerStatus> data)
    {
        super("Customer Status", new String[]{"ID", "Label"}, data);
    }

    public CustomerStatusEnhancedTableModel()
    {
        this(new ArrayList<>());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        CustomerStatus customerStatus = getData().get(rowIndex);

        if (customerStatus == null)
        {
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> customerStatus.getId();
            case 1 -> customerStatus.getLabel();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnIndex == 0 ? Integer.class : String.class;
    }
}
