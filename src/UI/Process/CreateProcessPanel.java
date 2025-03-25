package UI.Process;

import DataAccess.Process.ProcessDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DataAccess.Process.GetAllProcessesException;
import Model.Process;
import UI.Components.GridBagLayoutHelper;
import UI.Components.SearchByLabelPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateProcessPanel extends JPanel
{
    public CreateProcessPanel()
    {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Create a new process");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel searchForm = new JPanel(new GridBagLayout());
        GridBagLayoutHelper gridNewProcess = new GridBagLayoutHelper(searchForm);

        JTextField processLabelField = new JTextField();
        processLabelField.setPreferredSize(new Dimension(300, 50));
        gridNewProcess.addField("Process Label", processLabelField);

        JTextField processNumberField = new JTextField();
        processNumberField.setPreferredSize(new Dimension(300, 50));
        gridNewProcess.addField("Process Number", processNumberField);

        // Search for
        SearchByLabelPanel<Process> searchByLabelPanel;
        try
        {
            ProcessDBAccess processDBAccess = new ProcessDBAccess();
            ArrayList<Process> processes = processDBAccess.getAllProcesses();

            searchByLabelPanel = new SearchByLabelPanel<>(processes, Process::getLabel);
            gridNewProcess.addField("Search by label", searchByLabelPanel);

            // button to show selected process
            JButton showSelectedProcessButton = new JButton("Show selected process");
            showSelectedProcessButton.addActionListener(e -> {
                Process selectedProcess = (Process) searchByLabelPanel.getSelectedItem();
                if (selectedProcess != null)
                {
                    JOptionPane.showMessageDialog(null, selectedProcess.getLabel(), "Selected process", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No process selected", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            gridNewProcess.addField("", showSelectedProcessButton);

        } catch (DatabaseConnectionFailedException | GetAllProcessesException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


        add(searchForm, BorderLayout.CENTER);

    }
}
