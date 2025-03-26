package UI.Process;

import DataAccess.Customer.CustomerDBAccess;
import DataAccess.Employee.EmployeeDBAccess;
import DataAccess.Process.ProcessDBAccess;
import DataAccess.ProcessStatus.ProcessStatusDBAccess;
import DataAccess.Supplier.SupplierDBAccess;
import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DataAccess.Process.GetAllProcessesException;
import Exceptions.Employee.GetAllEmployeesException;
import Exceptions.ProcessStatus.GetAllProcessStatusException;
import Exceptions.Supplier.GetAllSuppliersException;
import Model.*;
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
        processLabelField.setPreferredSize(new Dimension(300, 30));
        gridNewProcess.addField("Process Label", processLabelField);

        JTextField processNumberField = new JTextField();
        processNumberField.setPreferredSize(new Dimension(300, 30));
        gridNewProcess.addField("Process Number", processNumberField);

        // Search for
        SearchByLabelPanel<Customer> customerSearch;
        try
        {
            CustomerDBAccess customerDBAccess = new CustomerDBAccess();
            ArrayList<Customer> customers = customerDBAccess.getAllCustomers();

            customerSearch = new SearchByLabelPanel<>(customers, "Search for a customer", customer -> customer.getLastName() + " - " + customer.getFirstName());
            gridNewProcess.addField("Customer", customerSearch);


        } catch (DatabaseConnectionFailedException | GetAllCustomersException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        SearchByLabelPanel<Supplier> supplierSearch;

        try
        {
            SupplierDBAccess supplierDBAccess = new SupplierDBAccess();
            ArrayList<Supplier> suppliers = supplierDBAccess.getAllSuppliers();

            supplierSearch = new SearchByLabelPanel<>(suppliers, "Search for a supplier", Supplier::getName);
            gridNewProcess.addField("Supplier", supplierSearch);
        } catch (DatabaseConnectionFailedException | GetAllSuppliersException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        SearchByLabelPanel<ProcessStatus> processStatusSearch;

        try
        {
            ProcessStatusDBAccess processDBAccess = new ProcessStatusDBAccess();
            ArrayList<ProcessStatus> processStatuses = processDBAccess.getAllProcessStatus();

            processStatusSearch = new SearchByLabelPanel<>(processStatuses, "", ProcessStatus::getLabel);
            gridNewProcess.addField("Process Status", processStatusSearch);
        } catch (DatabaseConnectionFailedException | GetAllProcessStatusException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        SearchByLabelPanel<Employee> employeeSearch;

        try
        {
            EmployeeDBAccess employeeDBAccess = new EmployeeDBAccess();
            ArrayList<Employee> employees = employeeDBAccess.getAllEmployees();

            employeeSearch = new SearchByLabelPanel<>(employees, "Search for an employee", employee -> employee.getLastName() + " " + employee.getFirstName());
            gridNewProcess.addField("Employee", employeeSearch);
        } catch (DatabaseConnectionFailedException | GetAllEmployeesException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        SearchByLabelPanel<Process> processSearch;

        try
        {
            ProcessDBAccess processDBAccess = new ProcessDBAccess();
            ArrayList<Process> processes = processDBAccess.getAllProcesses();

            processSearch = new SearchByLabelPanel<>(processes, "", process -> process.getLabel() + " " + process.getNumber());
            gridNewProcess.addField("Process", processSearch);
        } catch (DatabaseConnectionFailedException | GetAllProcessesException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Bttuon create
        JButton createButton = new JButton("Create Process");

        createButton.addActionListener(e -> {
            Customer customer = customerSearch.getSelectedItem();
            Supplier supplier = supplierSearch.getSelectedItem();
            ProcessStatus processStatus = processStatusSearch.getSelectedItem();
            Employee employee = employeeSearch.getSelectedItem();
            Process process = processSearch.getSelectedItem();


            try
            {
                ProcessDBAccess processDBAccess = new ProcessDBAccess();
                processDBAccess.createProcess(processLabelField.getText(), Integer.parseInt(processNumberField.getText()), customer.getId(), supplier.getId(), processStatus.getId(), employee.getId(), process.getId());
            } catch (DatabaseConnectionFailedException e1)
            {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(createButton, BorderLayout.SOUTH);
        add(searchForm, BorderLayout.CENTER);

    }
}
