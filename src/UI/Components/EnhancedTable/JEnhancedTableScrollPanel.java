package UI.Components.EnhancedTable;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;

public class JEnhancedTableScrollPanel extends JScrollPane
{
    private final JEnhancedTable table;

    public JEnhancedTableScrollPanel(TableModel model, JPanel target, int rows)
    {
        table = new JEnhancedTable(model);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        if(rows > 0)
        {
            setPreferredSize(new Dimension(getPreferredSize().width, table.getRowHeight() * rows));

            // if rows is greater than 0, then the table will have a fixed height
            setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        }

        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

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
        table.updateModel(model);
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

        table.addMouseListener(new java.awt.event.MouseAdapter()
        {
            //  ON WINDOWS, mousePressed does not trigger the popup menu
            // ON LINUX, mouseReleased does not trigger the popup menu,
            // so we need to handle both events
            @Override
            public void mousePressed(java.awt.event.MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    // check if a row is selected
                    if(table.getSelectedRow() != -1)
                        showPopup(e);
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    // check if a row is selected
                    if(table.getSelectedRow() != -1)
                        showPopup(e);
                }
            }

            private void showPopup(java.awt.event.MouseEvent e)
            {
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (hasFocus()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(2));
            g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
