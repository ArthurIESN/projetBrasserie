package UI.Components.EnhancedTable;

import UI.Theme.ThemeManager;
import UI.Theme.ThemeObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Date;
import java.util.Properties;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.*;


public class JEnhancedTable extends JTable implements ThemeObserver

{
    private ComponentListener resizeListener;
    private static Color backbroundRowColor = new Color(0, 0, 0, 0);
    private static Color alternateBackgroundRowColor = new Color(70, 70, 70);
    private static Color selectedRowColor = new Color(100, 100, 100);

    public JEnhancedTable(TableModel model)
    {
        super(model);

        ThemeManager.getInstance().addObserver(this);

        setModel(model);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // disable columns swapping
        getTableHeader().setReorderingAllowed(false);

        // disable sorter
        getTableHeader().setResizingAllowed(false);

        addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                Window window = SwingUtilities.getWindowAncestor(JEnhancedTable.this);
                if (window != null)
                {
                    resizeListener = new ComponentAdapter()
                    {
                        @Override
                        public void componentResized(ComponentEvent e)
                        {
                            updateColumnSize();
                        }
                    };
                    window.addComponentListener(resizeListener);
                }


            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                Window window = SwingUtilities.getWindowAncestor(JEnhancedTable.this);
                if (window != null && resizeListener != null)
                {
                    window.removeComponentListener(resizeListener);
                    resizeListener = null;
                }
            }
            @Override
            public void ancestorMoved(AncestorEvent event) {}
        });

        setDefaultRenderer(Object.class, new AlternateColumnRenderer());
        setDefaultRenderer(Number.class, new AlternateColumnRenderer());
        setDefaultRenderer(Boolean.class, new AlternateColumnRenderer());
        setDefaultRenderer(Date.class, new AlternateColumnRenderer());

        // Use the default table header renderer
        // Avoid getting the default header style with over and background color
        getTableHeader().setUI(new javax.swing.plaf.basic.BasicTableHeaderUI());
    }

    public static void onThemeChangedStatic(Properties themeProperties)
    {
        if (themeProperties != null)
        {
            alternateBackgroundRowColor = Color.decode(themeProperties.getProperty("EnhancedTable.alternateBackgroundRowColor"));
            selectedRowColor = Color.decode(themeProperties.getProperty("EnhancedTable.selectedRowColor"));
        }
    }

    @Override
    public void onThemeChanged(Properties themeProperties)
    {

    }

    // Update column size when this component is added to a container
    // use invokeLater to ensure that the component is fully initialized and visible before updating the column size
    // otherwise the table width will be 0 !!
    @Override
    public void addNotify() {
        super.addNotify();
        SwingUtilities.invokeLater(() ->
        {
            if (isShowing()) {
                updateColumnSize();
            }
        });
    }

    public void updateModel(TableModel model)
    {
        setModel(model);
        updateColumnSize();
        revalidate();
        repaint();
    }

    public void updateColumnSize()
    {
        TableColumnModel columnModel = getColumnModel();
        FontMetrics metrics = getFontMetrics(getFont());

        int tableWidth = getParent() != null ? getParent().getWidth() : getWidth();
        int totalColumnWidth = 0;

        int[] columnWidths = new int[columnModel.getColumnCount()];

        // Calculate the width of each column base on header width and cell content width
        // Column size is based on max width of header and cell content
        for (int i = 0; i < columnModel.getColumnCount(); i++)
        {
            TableColumn column = columnModel.getColumn(i);
            String columnName = getColumnName(i);
            int columnNameWidth = metrics.stringWidth(columnName) + 40;

            // Calculate the maximum width of the cells in this column
            int maxCellWidth = 0;
            for (int row = 0; row < getRowCount(); row++)
            {
                Object cellValue = getValueAt(row, i);
                int cellWidth = metrics.stringWidth(cellValue != null ? cellValue.toString() : "") + 20;
                maxCellWidth = Math.max(maxCellWidth, cellWidth);
            }

            int columnWidth = Math.max(columnNameWidth, maxCellWidth);
            columnWidths[i] = columnWidth;
            totalColumnWidth += columnWidth;
        }

        if (tableWidth > totalColumnWidth)
        {
            int extraSpace = tableWidth - totalColumnWidth;
            int extraPerColumn = extraSpace / columnModel.getColumnCount();

            // Distribute extra space evenly among all columns
            for (int i = 0; i < columnModel.getColumnCount(); i++)
            {
                columnWidths[i] += extraPerColumn;
            }
        }

        // Set the size of each column
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
            column.setMinWidth(columnWidths[i]);
        }
    }

    private static class AlternateColumnRenderer extends DefaultTableCellRenderer
    {
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
