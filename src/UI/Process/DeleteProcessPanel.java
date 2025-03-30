package UI.Process;

import Controller.AppController;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Process.GetAllProcessesException;
import Model.Process.Process;
import UI.Components.GridBagLayoutHelper;
import UI.Components.JEnhancedTableScrollPanel;
import UI.Components.SearchByLabelPanel;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

public class DeleteProcessPanel extends JPanel
{
    public DeleteProcessPanel(Process process)
    {
        JPanel searchForm = new JPanel(new GridBagLayout());
        GridBagLayoutHelper gridDeleteProcess = new GridBagLayoutHelper(searchForm);

        ArrayList<Process> processes = new ArrayList<>();

        try
        {
            processes = AppController.getAllProcesses();
        }
        catch (DatabaseConnectionFailedException | GetAllProcessesException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        SearchByLabelPanel<Process> processSearch = new SearchByLabelPanel<>( processes, "Search for a process", Process::getLabel);
        gridDeleteProcess.addField("Process", processSearch);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e ->
        {
            //@todo: delete the process
        });

        gridDeleteProcess.addField(deleteButton);

        add(searchForm, BorderLayout.CENTER);

        JEnhancedTableScrollPanel tableScrollPanel = new JEnhancedTableScrollPanel(new ProcessTableModel(), this, 4);
        processSearch.onSelectedItemChange(() ->
        {
            Process selectedProcess = processSearch.getSelectedItem();
            if (selectedProcess != null)
            {
                tableScrollPanel.updateModel(new ProcessTableModel(selectedProcess));
            }
        });
        add(tableScrollPanel);

        if(process != null)
        {
            processSearch.setSelectedItem(process);
        }
    }

    public DeleteProcessPanel()
    {
        this(null);
    }
}
