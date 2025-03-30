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
import UI.Components.JEnhancedTableScrollPanel;

public class ReadProcessPanel extends JPanel
{
    public ReadProcessPanel(ProcessPanel processPanel)
    {
        ArrayList<Process> processes;

        try
        {
            processes = AppController.getAllProcesses();
        }
        catch (DatabaseConnectionFailedException | GetAllProcessesException e)
        {
            processes = new ArrayList<>();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        ArrayList<String> menuItems = new ArrayList<>();
        menuItems.add("Update");
        menuItems.add("Delete");

        JEnhancedTableScrollPanel tableScrollPanel = new JEnhancedTableScrollPanel(new ProcessTableModel(processes), this);

        ArrayList<Process> finalProcesses = processes;
        tableScrollPanel.addMenuOnRows(menuItems, action ->
        {
            switch (action.getActionCommand())
            {
                case "Update" -> processPanel.updateContent(2, finalProcesses.get(tableScrollPanel.getTable().getSelectedRow()));
                case "Delete" -> processPanel.updateContent(3, finalProcesses.get(tableScrollPanel.getTable().getSelectedRow()));
            }
        });

        add(tableScrollPanel, BorderLayout.CENTER);
    }
}
