package UI.Components;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class TableModelMaker extends AbstractTableModel {
    private final ArrayList<AbstractEnhancedTableModel<?>> tableModels = new ArrayList<>();
    private JEnhancedTableScrollPanel table;
    private int currentColumnIndex = 0;

    public TableModelMaker()
    {

    }

    public void setTable(JEnhancedTableScrollPanel table) {
        this.table = table;
        addEventOnAllFirstColumnsOfEachTableModel();
    }

    public void addTableModel(AbstractEnhancedTableModel<?> tableModel) {
        tableModels.add(tableModel);
    }

    public void addEventOnAllFirstColumnsOfEachTableModel() {
        JTable jTable = table.getTable();

        // Supprime les anciens listeners
        for (MouseListener ml : jTable.getTableHeader().getMouseListeners()) {
            jTable.getTableHeader().removeMouseListener(ml);
        }

        jTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedColumn = jTable.columnAtPoint(e.getPoint());
                System.out.println("Clicked column: " + clickedColumn);

                int currentColumnIndex = 0;

                for (int i = 0; i < tableModels.size(); i++) {
                    AbstractEnhancedTableModel<?> model = tableModels.get(i);

                    int modelColumnCount;
                    if (i == 0) {
                        modelColumnCount = model.getColumnNames().length;
                    } else {
                        modelColumnCount = model.isOpen() ? model.getColumnNames().length : 1;
                    }

                    if (clickedColumn == currentColumnIndex && i != 0)
                    {

                        model.setOpen(!model.isOpen());

                        fireTableStructureChanged();
                        table.updateModel(TableModelMaker.this);
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

        for (int i = 1; i < tableModels.size(); i++)
        {
            AbstractEnhancedTableModel<?> tableModel = tableModels.get(i); // process status

            if (tableModel.isOpen()) {
                if (columnIndex - currentColumnIndex < tableModel.getColumnNames().length)
                {
                    // Return the column name of the current table model and adding the class name at the beginning
                    return tableModel.getClassName() + " " + tableModel.getColumnNames()[columnIndex - currentColumnIndex];
                } else
                {
                    currentColumnIndex += tableModel.getColumnNames().length;
                }
            } else
            {
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
