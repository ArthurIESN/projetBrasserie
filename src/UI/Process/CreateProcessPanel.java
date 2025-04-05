package UI.Process;

import javax.swing.*;
import java.awt.*;

public class CreateProcessPanel extends JPanel
{
    public CreateProcessPanel()
    {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Create a new process");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        ProcessModelPanel processModelPanel = new ProcessModelPanel(false, false);
        processModelPanel.setButtonText("Create Process");

        add(processModelPanel, BorderLayout.CENTER);


        /*
        createButton.addActionListener(e -> {
            Customer customer = customerSearch.getSelectedItem();
            Supplier supplier = supplierSearch.getSelectedItem();
            ProcessStatus processStatus = processStatusSearch.getSelectedItem();
            Employee employee = employeeSearch.getSelectedItem();

            java.util.Date date = (Date) dateSpinner.getValue();

            Process process = new Process(null, processLabelField.getText(), Integer.parseInt(processNumberField.getText()), new java.util.Date(date.getTime()), supplier, typeSearch.getSelectedItem(), processStatus, employee, customer);


            try
            {
                AppController.createProcess(process);

                JOptionPane.showMessageDialog(null, "Process created", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (DatabaseConnectionFailedException | CreateProcessException ee)
            {
                JOptionPane.showMessageDialog(null, ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        */

    }
}
