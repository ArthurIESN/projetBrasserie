package UI.Models;

import Model.ProcessType.ProcessType;
import UI.Components.EnhancedTable.AbstractEnhancedTableModel;

import java.util.ArrayList;

public class ProcessTypeEnhancedTableModel extends AbstractEnhancedTableModel<ProcessType>
{

    public ProcessTypeEnhancedTableModel(ArrayList<ProcessType> processType)
    {
        super("Process Type", new String[]{"ID", "Label"}, processType);
    }

    public ProcessTypeEnhancedTableModel()
    {
        this(new ArrayList<ProcessType>());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        ProcessType processType = getData().get(rowIndex);

        if (processType == null)
        {
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> processType.getId();
            case 1 -> processType.getLabel();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return switch (columnIndex)
        {
            case 0 -> Integer.class;
            default -> String.class;
        };
    }
}
