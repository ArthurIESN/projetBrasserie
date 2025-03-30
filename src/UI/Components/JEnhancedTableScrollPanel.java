package UI.Components;

import UI.Components.JEnhancedTable;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;

public class JEnhancedTableScrollPanel extends JScrollPane
{
    private final JEnhancedTable table;
    private final int rows;
    public JEnhancedTableScrollPanel(TableModel model, JPanel target, int rows)
    {
        table = new JEnhancedTable(model);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.rows = rows;
        if(rows > 0)
        {
            setPreferredSize(new Dimension(getPreferredSize().width, table.getRowHeight() * rows));

            // if rows is greater than 0, then the table will have a fixed height
            setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        }

        target.setLayout(new BoxLayout(target, BoxLayout.Y_AXIS));
        setViewportView(table);
    }

    public JEnhancedTableScrollPanel(TableModel model, JPanel target)
    {
        this(model, target, 0);
    }

    public JEnhancedTable getTable()
    {
        return table;
    }

    public void updateModel(TableModel model)
    {
        table.setModel(model);
        table.updateColumnSize();
        table.revalidate();
    }

    public void addMenuOnRows(ArrayList<String> menuItems, ActionListener actionListener)
    {
        JPopupMenu popupMenu = new JPopupMenu();
        for(String menuItem : menuItems)
        {
            JMenuItem item = new JMenuItem(menuItem);
            item.setActionCommand(menuItem);
            item.addActionListener(actionListener);
            popupMenu.add(item);
        }

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger())
                {
                    showPopup(e);
                }
            }

            private void showPopup(java.awt.event.MouseEvent e)
            {
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
}
