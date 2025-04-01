package UI.Process;

import Controller.AppController;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Process.DeleteProcessException;
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
    public DeleteProcessPanel(ProcessPanel processPanel, Process process)
    {
        GridBagLayoutHelper gridDeleteProcess = new GridBagLayoutHelper();

        ArrayList<Process> processes = new ArrayList<>();

        try
        {
            processes = AppController.getAllProcesses();
        }
        catch (DatabaseConnectionFailedException | GetAllProcessesException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        SearchByLabelPanel<Process> processSearch = new SearchByLabelPanel<>( processes, searchProcess -> searchProcess.getLabel() + " - " + searchProcess.getNumber() + " - " + searchProcess.getProcessStatus().getLabel());
        processSearch.getSearchField().setPlaceholder("Search for a process");

        gridDeleteProcess.addField("Process", processSearch);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e ->
        {

            // Sur de vouloir supprimer le processus
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this process?", "Warning", JOptionPane.YES_NO_OPTION);

            if(dialogResult == JOptionPane.NO_OPTION)
            {
                return;
            }

            Process selectedProcess = processSearch.getSelectedItem();
            if (selectedProcess != null)
            {
                try
                {
                    AppController.deleteProcess(selectedProcess.getId());

                    JOptionPane.showMessageDialog(null, "Process deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // refresh the panel
                    processPanel.updateContent(3, null);

                }
                catch (DatabaseConnectionFailedException | DeleteProcessException e1)
                {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gridDeleteProcess.addField(deleteButton);

        add(gridDeleteProcess, BorderLayout.CENTER);

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

    public DeleteProcessPanel(ProcessPanel processPanel)
    {
        this(processPanel, null);
    }
}
