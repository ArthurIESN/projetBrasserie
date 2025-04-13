package UI.Process;

import Controller.AppController;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Process.CreateProcessException;
import Model.Process.Process;

import javax.swing.*;
import java.awt.*;

public class CreateProcessPanel extends JPanel
{
    private final ProcessModelPanel processModelPanel;

    public CreateProcessPanel()
    {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Create a new process");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        processModelPanel = new ProcessModelPanel(false, false);
        processModelPanel.setButtonText("Create Process");

        add(processModelPanel, BorderLayout.CENTER);

        processModelPanel.onButtonClicked(e -> createProcess());

    }

    private void createProcess()
    {
        if(processModelPanel.getProcessLabelField().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please fill in the process label", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(processModelPanel.getProcessNumberField().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please fill in the process number", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(processModelPanel.getDateField().getDate() == null)
        {
            JOptionPane.showMessageDialog(this, "Please fill in the process date", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(processModelPanel.getProcessStatusSearch().getSelectedItem() == null)
        {
            JOptionPane.showMessageDialog(this, "Please select a process status", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(processModelPanel.getTypeSearch().getSelectedItem() == null)
        {
            JOptionPane.showMessageDialog(this, "Please select a process type", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // we won't check for customer, supplier and employee, as they are optional

        Process process = new Process(null,
                processModelPanel.getProcessLabelField().getText(),
                processModelPanel.getProcessNumberField().getInt(),
                processModelPanel.getDateField().getDate(),
                processModelPanel.getSupplierSearch().getSelectedItem(),
                processModelPanel.getTypeSearch().getSelectedItem(),
                processModelPanel.getProcessStatusSearch().getSelectedItem(),
                processModelPanel.getEmployeeSearch().getSelectedItem(),
                processModelPanel.getCustomerSearch().getSelectedItem()
        );

        try
        {
            AppController.createProcess(process);
            JOptionPane.showMessageDialog(this, "Process created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (CreateProcessException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
