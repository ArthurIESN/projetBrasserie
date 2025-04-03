package UI.Process;

import Controller.AppController;
import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Employee.GetAllEmployeesException;
import Exceptions.Process.CreateProcessException;
import Exceptions.ProcessStatus.GetAllProcessStatusException;
import Exceptions.Supplier.GetAllSuppliersException;
import Exceptions.ProcessType.GetAllProcessTypesException;

import Model.Process.Process;
import Model.Customer.Customer;
import Model.Employee.Employee;
import Model.Process.MakeProcess;
import Model.ProcessStatus.ProcessStatus;
import Model.Supplier.Supplier;
import Model.ProcessType.ProcessType;

import UI.Components.GridBagLayoutHelper;
import UI.Components.JEnhancedTextField;
import UI.Components.SearchByLabelPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

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
