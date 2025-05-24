package UI.Models;

import Model.EmployeeStatus.EmployeeStatus;
import UI.Components.EnhancedTable.AbstractEnhancedTableModel;

import java.util.ArrayList;

public class EmployeeStatusEnhancedTableModel extends AbstractEnhancedTableModel<EmployeeStatus>
{
    public EmployeeStatusEnhancedTableModel(ArrayList<EmployeeStatus> employeeStatus)
    {
        super("Employee Status", new String[]{"ID", "Label"}, employeeStatus);
    }

    public EmployeeStatusEnhancedTableModel()
    {
        this(new ArrayList<EmployeeStatus>());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        EmployeeStatus employeeStatus = getData().get(rowIndex);

        if (employeeStatus == null)
        {
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> employeeStatus.getId();
            case 1 -> employeeStatus.getLabel();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnIndex == 0 ? Integer.class : String.class;
    }
}
