package UI.Components;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
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

        // addAncestorListener to wait until the table is added to a window
        addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event)
            {
                Window window = SwingUtilities.getWindowAncestor(JEnhancedTable.this);
                if (window != null) {

                    // When window is resized, update column size
                    window.addComponentListener(new ComponentAdapter()
                    {
                        @Override
                        public void componentResized(ComponentEvent e)
                        {
                            updateColumnSize();
                        }
                    });
                }
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {}
            @Override
            public void ancestorMoved(AncestorEvent event) {}
        });

        // delay the first updateColumnSize to wait for the table to be added to a window and then update the column size
        SwingUtilities.invokeLater(this::updateColumnSize);
    }

    public void updateColumnSize()
    {
        TableColumnModel columnModel = getColumnModel();
        FontMetrics metrics = getFontMetrics(getFont());
        int tableWidth = getParent() != null ? getParent().getWidth() : getWidth();
        int totalColumnWidth = 0;

        int[] columnWidths = new int[columnModel.getColumnCount()];

        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            String columnName = getColumnName(i);
            int columnNameWidth = metrics.stringWidth(columnName) + 40;

            int maxCellWidth = 0;
            for (int row = 0; row < getRowCount(); row++) {
                Object cellValue = getValueAt(row, i);
                int cellWidth = metrics.stringWidth(cellValue != null ? cellValue.toString() : "") + 20;
                maxCellWidth = Math.max(maxCellWidth, cellWidth);
            }

            int columnWidth = Math.max(columnNameWidth, maxCellWidth);
            columnWidths[i] = columnWidth;
            totalColumnWidth += columnWidth;
        }

        if (tableWidth > totalColumnWidth) {
            int extraSpace = tableWidth - totalColumnWidth;
            int extraPerColumn = extraSpace / columnModel.getColumnCount();

            for (int i = 0; i < columnModel.getColumnCount(); i++) {
                columnWidths[i] += extraPerColumn;
            }
        }

        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
            column.setMinWidth(columnWidths[i]);
        }
    }
}
