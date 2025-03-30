package UI.Components;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class JEnhancedTable extends JTable
{
    public JEnhancedTable(TableModel model)
    {
        super(model);
        setModel(model);
        setAutoCreateRowSorter(true);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        updateColumnSize();
    }

    public void updateColumnSize()
    {
        TableColumnModel columnModel = getColumnModel();
        FontMetrics metrics = getFontMetrics(getFont());

        for (int i = 0; i < columnModel.getColumnCount(); i++)
        {
            TableColumn column = columnModel.getColumn(i);
            String columnName = getColumnName(i);

            // Calculez la largeur du nom de la colonne avec une marge supplémentaire
            int columnNameWidth = metrics.stringWidth(columnName) + 40; // marge supplémentaire de 40 pixels

            // Calculez la largeur maximale des valeurs des cellules
            int maxCellWidth = 0;
            for (int row = 0; row < getRowCount(); row++)
            {
                Object cellValue = getValueAt(row, i);
                int cellWidth = metrics.stringWidth(cellValue != null ? cellValue.toString() : "") + 20;
                maxCellWidth = Math.max(maxCellWidth, cellWidth);
            }

            // Définissez la largeur de la colonne en utilisant la plus grande valeur entre la largeur du nom de la colonne (avec marge) et la largeur maximale des valeurs des cellules
            int columnWidth = Math.max(columnNameWidth, maxCellWidth);
            column.setPreferredWidth(columnWidth);
            column.setMinWidth(columnWidth);
        }
    }
}
