package UI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Date;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class JEnhancedTable extends JTable
{
    private static final Color backbroundRowColor = new Color(0, 0, 0, 0);
    private static final Color alternateBackgroundRowColor = new Color(70, 70, 70);
    private static final Color selectedRowColor = new Color(100, 100, 100);

    public JEnhancedTable(TableModel model)
    {
        super(model);
        setModel(model);
        setAutoCreateRowSorter(true);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event)
            {
                Window window = SwingUtilities.getWindowAncestor(JEnhancedTable.this);
                if (window != null) {
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

        SwingUtilities.invokeLater(this::updateColumnSize);

        setDefaultRenderer(Object.class, new AlternateColumnRenderer());
        setDefaultRenderer(Number.class, new AlternateColumnRenderer());
        setDefaultRenderer(Boolean.class, new AlternateColumnRenderer());
        setDefaultRenderer(Date.class, new AlternateColumnRenderer());

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

    private static class AlternateColumnRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!isSelected)
            {
                cell.setBackground(column % 2 == 0 ? backbroundRowColor : alternateBackgroundRowColor);
            }
            else
            {
                cell.setBackground(selectedRowColor);
            }
            return cell;
        }
    }
}
