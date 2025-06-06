package UI.Models;

import Model.ProcessStatus.ProcessStatus;
import UI.Components.EnhancedTable.AbstractEnhancedTableModel;

import java.util.ArrayList;

public class ProcessStatusEnhancedTableModel extends AbstractEnhancedTableModel<ProcessStatus>
{
    public ProcessStatusEnhancedTableModel(ArrayList<ProcessStatus> data)
    {
        super("Process Status", new String[]{"ID", "Label"}, data);
    }

    public ProcessStatusEnhancedTableModel()
    {
        this(new ArrayList<>());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        ProcessStatus processStatus = getData().get(rowIndex);

        if (processStatus == null)
        {
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> processStatus.getId();
            case 1 -> processStatus.getLabel();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return switch (columnIndex) {
            case 0, 2 -> Integer.class;
            case 3 -> java.util.Date.class;
            default -> String.class;
        };
    }
}
