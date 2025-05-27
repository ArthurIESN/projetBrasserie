package UI.Components.EnhancedTable;

public interface EnhancedTableModelInterface
{
    String className = null;
    String[] columnNames = null;
    Object[] data = null;
    boolean isOpen = false;

    Object getValueAt(int rowIndex, int columnIndex);
    Class<?> getColumnClass(int columnIndex);
}
