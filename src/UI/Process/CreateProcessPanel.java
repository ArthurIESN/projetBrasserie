package UI.Process;

import Controller.Process.ProcessController;
import Exceptions.Process.CreateProcessException;
import Exceptions.Process.ProcessException;
import Model.Process.Process;

import javax.swing.*;
import java.awt.*;

public class CreateProcessPanel extends JPanel
{
    private final ProcessModelPanel processModelPanel;

    public CreateProcessPanel(ProcessPanel processPanel)
    {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Create a new process");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        processModelPanel = new ProcessModelPanel(false);
        processModelPanel.setButtonText("Create Process");

        add(processModelPanel, BorderLayout.CENTER);

        processModelPanel.onButtonClicked(e -> createProcess());

    }

    private void createProcess()
    {
        if(processModelPanel.isProcessInvalid()) return;

        try
        {
            Process process = new Process(10,
                    processModelPanel.getProcessLabelField().getText(),
                    processModelPanel.getProcessNumberField().getInt(),
                    processModelPanel.getSupplierSearch().getSelectedItem(),
                    processModelPanel.getTypeSearch().getSelectedItem(),
                    processModelPanel.getProcessStatusSearch().getSelectedItem(),
                    processModelPanel.getEmployeeSearch().getSelectedItem(),
                    processModelPanel.getCustomerSearch().getSelectedItem()
            );

            ProcessController.createProcess(process);
            JOptionPane.showMessageDialog(this, "Process created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (ProcessException | CreateProcessException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
