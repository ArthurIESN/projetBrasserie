package UI.Components;

import java.util.ArrayList;

public abstract class AbstractEnhancedTableModel<T> implements EnhancedTableModelInterface
{
    private final String className;
    private final String[] columnNames;
    private final ArrayList<T> data;
    private boolean isOpen = false;

    public AbstractEnhancedTableModel(String className, String[] columnNames, ArrayList<T> data)
    {
        this.className = className;
        this.columnNames = columnNames;
        this.data = data;
    }

    @Override
    public abstract Object getValueAt(int rowIndex, int columnIndex);

    @Override
    public abstract Class<?> getColumnClass(int columnIndex);

    public String getClassName()
    {
        return className;
    }

    public String[] getColumnNames()
    {
        return columnNames;
    }

    public ArrayList<T> getData()
    {
        return data;
    }

    public void setData(ArrayList<T> data)
    {
        this.data.clear();
        this.data.addAll(data);
    }

    public boolean isOpen()
    {
        return isOpen;
    }

    public void setOpen(boolean open)
    {
        isOpen = open;
    }
}
