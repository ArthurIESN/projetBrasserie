package UI.Models;

import UI.Components.EnhancedTable.AbstractEnhancedTableModel;
import Model.Process.Process;

import java.util.ArrayList;

public class ProcessEnhancedTableModel extends AbstractEnhancedTableModel<Process>
{
    public ProcessEnhancedTableModel(ArrayList<Process> data)
    {
        super("Process", new String[]{"ID", "Label", "Number"}, data);
    }

    public ProcessEnhancedTableModel()
    {
        this(new ArrayList<>());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Process process = getData().get(rowIndex);

        if (process == null)
        {
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> process.getId();
            case 1 -> process.getLabel();
            case 2 -> process.getNumber();
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

    @Override
    public String getClassName()
    {
        return super.getClassName();
    }

    @Override
    public String[] getColumnNames()
    {
        return super.getColumnNames();
    }

    @Override
    public ArrayList<Process> getData()
    {
        return super.getData();
    }
}
