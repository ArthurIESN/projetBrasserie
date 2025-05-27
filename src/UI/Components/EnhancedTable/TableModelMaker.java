package UI.Components.EnhancedTable;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TableModelMaker extends AbstractTableModel {
    private final ArrayList<AbstractEnhancedTableModel<?>> tableModels = new ArrayList<>();
    private JEnhancedTableScrollPanel table;

    public TableModelMaker()
    {

    }

    public void setTable(JEnhancedTableScrollPanel table)
    {
        this.table = table;
        addMouseClickedListener();
    }

    public void addTableModel(AbstractEnhancedTableModel<?> tableModel) {
        tableModels.add(tableModel);
    }


    private void updateHeadingColor() {
        JTable jTable = table.getTable();

        int currentColumnIndex = 0;

        for (int i = 0; i < tableModels.size(); i++) {
            AbstractEnhancedTableModel<?> model = tableModels.get(i);
            int columnIndex = (i == 0) ? 0 : currentColumnIndex;

            if (columnIndex < jTable.getColumnModel().getColumnCount()) {
                TableColumn column = jTable.getColumnModel().getColumn(columnIndex);
                column.setHeaderRenderer(new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        Component header = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        header.setBackground(new Color(0x888888));
                        header.setForeground(Color.WHITE);
                        return header;
                    }
                });
            }

            currentColumnIndex += model.isOpen() || i == 0 ? model.getColumnNames().length : 1;
        }
    }

    public void addMouseClickedListener()
    {
        JTable jTable = table.getTable();

        updateHeadingColor();

        // Delete all mouse listeners on the table header
        // This is to avoid multiple listeners being added when the table is updated
        for (MouseListener ml : jTable.getTableHeader().getMouseListeners()) {
            jTable.getTableHeader().removeMouseListener(ml);
        }

        // Open/close the table model when clicking on the right column
        jTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int clickedColumn = jTable.columnAtPoint(e.getPoint());

                int currentColumnIndex = 0;

                for (int i = 0; i < tableModels.size(); i++) {
                    AbstractEnhancedTableModel<?> model = tableModels.get(i);

                    int modelColumnCount;
                    if (i == 0) {
                        modelColumnCount = model.getColumnNames().length;
                    } else {
                        modelColumnCount = model.isOpen() ? model.getColumnNames().length : 1;
                    }

                    if (clickedColumn == currentColumnIndex && i != 0) {

                        model.setOpen(!model.isOpen());

                        fireTableStructureChanged();
                        table.updateModel(TableModelMaker.this);
                        updateHeadingColor();
                        return;
                    }

                    currentColumnIndex += modelColumnCount;
                }
            }
        });
    }


    @Override
    public int getRowCount() {
        if (tableModels.isEmpty()) {
            return 0;
        }

        return tableModels.getFirst().getData().size();
    }

    @Override
    public int getColumnCount() {
        // if table is closed, only first column is shown.
        if (tableModels.isEmpty()) {
            return 0;
        }

        // first data is always shown
        int columnCount = tableModels.getFirst().getColumnNames().length;

        for (int i = 1; i < tableModels.size(); i++) {
            AbstractEnhancedTableModel<?> tableModel = tableModels.get(i);
            if (tableModel.isOpen()) {
                columnCount += tableModel.getColumnNames().length;
            } else {
                columnCount++; // only first column is shown (which is often the ID)
            }
        }

        return columnCount;
    }

    @Override
    public String getColumnName(int columnIndex) {
        // if table is closed, only first column is shown.
        if (tableModels.isEmpty()) {
            return null;
        }

        if (tableModels.getFirst().getColumnNames().length > columnIndex) {
            return tableModels.getFirst().getColumnNames()[columnIndex];
        }

        int currentColumnIndex = tableModels.getFirst().getColumnNames().length;

        for (int i = 1; i < tableModels.size(); i++) {
            AbstractEnhancedTableModel<?> tableModel = tableModels.get(i); // process status

            if (tableModel.isOpen()) {
                if (columnIndex - currentColumnIndex < tableModel.getColumnNames().length) {
                    // Return the column name of the current table model and adding the class name at the beginning
                    return tableModel.getClassName() + " " + tableModel.getColumnNames()[columnIndex - currentColumnIndex];
                } else {
                    currentColumnIndex += tableModel.getColumnNames().length;
                }
            } else {
                if (columnIndex == currentColumnIndex) {
                    return tableModel.getClassName() + " " + tableModel.getColumnNames()[0];
                }

                currentColumnIndex++;
            }
        }

        return "ERROR NO NAME";

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // if table is closed, only first column is shown.
        if (tableModels.isEmpty()) {
            return null;
        }

        if (tableModels.getFirst().getColumnNames().length > columnIndex) {
            return tableModels.getFirst().getValueAt(rowIndex, columnIndex);
        }

        int currentColumnIndex = tableModels.getFirst().getColumnNames().length;

        for (int i = 1; i < tableModels.size(); i++) {
            AbstractEnhancedTableModel<?> tableModel = tableModels.get(i);
            if (tableModel.isOpen()) {
                if (columnIndex - currentColumnIndex < tableModel.getColumnNames().length) {
                    return tableModel.getValueAt(rowIndex, columnIndex - currentColumnIndex);
                } else {
                    currentColumnIndex += tableModel.getColumnNames().length;
                }
            } else {
                if (columnIndex == currentColumnIndex) {
                    return tableModel.getValueAt(rowIndex, 0);
                }

                currentColumnIndex++;
            }
        }

        return null;
    }
}
