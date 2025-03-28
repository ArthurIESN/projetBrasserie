package UI.Process;

import Controller.AppController;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Process.GetAllProcessesException;
import Model.Process.Process;

public class ReadProcessPanel extends JPanel
{
    public ReadProcessPanel()
    {
        ArrayList<Process> processes;

        try
        {
            processes = AppController.getAllProcesses();
        }
        catch (DatabaseConnectionFailedException | GetAllProcessesException e)
        {
            processes = new ArrayList<>();
        }

        JTable table = new JTable();
        table.setModel(new ProcessTableModel(processes));
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Empêche JTable de forcer le redimensionnement

// Ajuster la largeur des colonnes selon la taille du texte des en-têtes
        TableColumnModel columnModel = table.getColumnModel();
        FontMetrics metrics = table.getFontMetrics(table.getFont());

        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            String columnName = table.getColumnName(i);

            int columnWidth = metrics.stringWidth(columnName) + 20; // Ajoute une marge
            column.setPreferredWidth(columnWidth);
            column.setMinWidth(columnWidth);
        }

        // Créer le menu contextuel
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Update");
        JMenuItem menuItem2 = new JMenuItem("Delete");
        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);

        // Ajouter un MouseListener pour détecter les clics droits
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
                }
            }

            private void showPopup(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());
                if (!table.isRowSelected(row)) {
                    table.changeSelection(row, column, false, false);
                }
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

// Ajouter le JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



    }
}
