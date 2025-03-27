package UI.Process;

import Controller.AppController;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
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

// Ajouter le JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



    }
}
