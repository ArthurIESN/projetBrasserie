package UI.Process;

import Controller.AppController;
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

        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        ArrayList<ProcessStatus> processStatuses = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();

        try
        {
            customers = AppController.getAllCustomers();
            suppliers = AppController.getAllSuppliers();
            processStatuses = AppController.getAllProcessStatus();
            employees = AppController.getAllEmployees();

        } catch (DatabaseConnectionFailedException | GetAllCustomersException | GetAllSuppliersException | GetAllProcessStatusException | GetAllEmployeesException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        SearchByLabelPanel<Customer> customerSearch = new SearchByLabelPanel<>(customers, "Search for a customer", customer -> customer.getFirstName() + " " + customer.getLastName());
        SearchByLabelPanel<Supplier> supplierSearch = new SearchByLabelPanel<>(suppliers, "Search for a supplier", Supplier::getName);
        SearchByLabelPanel<ProcessStatus> processStatusSearch = new SearchByLabelPanel<>(processStatuses, "Search for a process status", ProcessStatus::getLabel);
        SearchByLabelPanel<Employee> employeeSearch = new SearchByLabelPanel<>(employees, "Search for an employee", employee -> employee.getFirstName() + " " + employee.getLastName());

        gridNewProcess.addField("Customer", customerSearch);
        gridNewProcess.addField("Supplier", supplierSearch);
        gridNewProcess.addField("Process Status", processStatusSearch);
        gridNewProcess.addField("Employee", employeeSearch);


        // Bttuon create
        JButton createButton = new JButton("Create Process");

        createButton.addActionListener(e -> {
            Customer customer = customerSearch.getSelectedItem();
            Supplier supplier = supplierSearch.getSelectedItem();
            ProcessStatus processStatus = processStatusSearch.getSelectedItem();
            Employee employee = employeeSearch.getSelectedItem();


            // show in jOptionPane
            JOptionPane.showMessageDialog(null, "Customer: " + customer.getFirstName() + " " + customer.getLastName() + "\n" +
                    "Supplier: " + supplier.getName() + "\n" +
                    "Process Status: " + processStatus.getLabel() + "\n" +
                    "Employee: " + employee.getFirstName() + " " + employee.getLastName() + "\n" +
                    "Process Label: " + processLabelField.getText() + "\n" +
                    "Process Number: " + processNumberField.getText() + "\n"
            );

        });

        add(createButton, BorderLayout.SOUTH);
        add(searchForm, BorderLayout.CENTER);

    }
}
