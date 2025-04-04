package UI.Components;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TableModelMaker extends AbstractTableModel
{
    private final ArrayList<AbstractEnhancedTableModel<?>> tableModels = new ArrayList<>();
    private JEnhancedTableScrollPanel table;

    public TableModelMaker()
    {

    }

    public void setTable(JEnhancedTableScrollPanel table)
    {
        this.table = table;
        addEventOnAllFirstColumnsOfEachTableModel();
    }

    public void addTableModel(AbstractEnhancedTableModel<?> tableModel)
    {
        tableModels.add(tableModel);
    }

    public void addEventOnAllFirstColumnsOfEachTableModel()
    {
        // Add for eahch first column of each table model
        int currentColumnIndex = 0;
        for (AbstractEnhancedTableModel<?> tableModel : tableModels)
        {
            // Add event listener on table at currentColumnIndex
            // When clicked, just print something for now
            int finalCurrentColumnIndex = currentColumnIndex;
            table.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int column = table.getTable().columnAtPoint(e.getPoint());
                    if (column == finalCurrentColumnIndex)
                    {
                        // open/close the table model
                        tableModel.setOpen(!tableModel.isOpen());

                        fireTableStructureChanged();
                        // update the table
                        table.updateModel(TableModelMaker.this);

                        // revalidate the table with new getColumnCount


                    }
                }
            });


            currentColumnIndex += tableModel.getColumnNames().length;
        }
    }

    @Override
    public int getRowCount()
    {
        if(tableModels.isEmpty())
        {
            return 0;
        }

        return tableModels.getFirst().getData().size();
    }

    @Override
    public int getColumnCount()
    {
        // if table is closed, only first column is shown.
        if(tableModels.isEmpty())
        {
            return 0;
        }

        // first data is always shown
        int columnCount = columnCount = tableModels.getFirst().getColumnNames().length;

        for (int i = 1; i < tableModels.size(); i++)
        {
            AbstractEnhancedTableModel<?> tableModel = tableModels.get(i);
            if (tableModel.isOpen())
            {
                columnCount += tableModel.getColumnNames().length;
            }
            else{
                columnCount++; // only first column is shown (which is often the ID)
            }
        }

        return columnCount;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        // if table is closed, only first column is shown.
        if(tableModels.isEmpty())
        {
            return null;
        }


        if(tableModels.getFirst().getColumnNames().length > columnIndex)
        {
            return tableModels.getFirst().getColumnNames()[columnIndex];
        }

        int currentColumnIndex = tableModels.getFirst().getColumnNames().length;

        for(int i = 1; i < tableModels.size(); i++)
        {
            AbstractEnhancedTableModel<?> tableModel = tableModels.get(i);
            if (tableModel.isOpen())
            {
                if (columnIndex - currentColumnIndex < tableModel.getColumnNames().length)
                {
                    // Return the column name of the current table model and adding the class name at the beginning
                    return tableModel.getClassName() + " " + tableModel.getColumnNames()[columnIndex - currentColumnIndex];
                }
                else
                {
                    currentColumnIndex += tableModel.getColumnNames().length;
                }
            }
            else
            {
                if(columnIndex == currentColumnIndex)
                {
                    return tableModel.getClassName() + " " + tableModel.getColumnNames()[0];
                }

                currentColumnIndex++;
            }
        }

        return "ERROR NO NAME";

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        // if table is closed, only first column is shown.
        if(tableModels.isEmpty())
        {
            return null;
        }

        if(tableModels.getFirst().getColumnNames().length > columnIndex)
        {
            return tableModels.getFirst().getValueAt(rowIndex, columnIndex);
        }

        int currentColumnIndex = tableModels.getFirst().getColumnNames().length;

        for(int i = 1; i < tableModels.size(); i++)
        {
            AbstractEnhancedTableModel<?> tableModel = tableModels.get(i);
            if (tableModel.isOpen())
            {
                if (columnIndex - currentColumnIndex < tableModel.getColumnNames().length)
                {
                    return tableModel.getValueAt(rowIndex, columnIndex - currentColumnIndex);
                }
                else
                {
                    currentColumnIndex += tableModel.getColumnNames().length;
                }
            }
            else
            {
                if(columnIndex == currentColumnIndex)
                {
                    return tableModel.getValueAt(rowIndex, 0);
                }

                currentColumnIndex++;
            }
        }

        return null;
    }
}
