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

        GridBagLayoutHelper gridNewProcess = new GridBagLayoutHelper();

        JEnhancedTextField processLabelField = new JEnhancedTextField();
        processLabelField.setPlaceholder("Process Label");
        gridNewProcess.addField("Process Label", processLabelField);

        JEnhancedTextField processNumberField = new JEnhancedTextField();
        processNumberField.setPlaceholder("Process Number");
        gridNewProcess.addField("Process Number", processNumberField);

        // date
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(dateModel);

        // Définissez le format de la date
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        // Ajoutez le champ de saisie de date au formulaire
        gridNewProcess.addField("Date", dateSpinner);

        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        ArrayList<ProcessStatus> processStatuses = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<ProcessType> types = new ArrayList<>();

        try
        {
            customers = AppController.getAllCustomers();
            suppliers = AppController.getAllSuppliers();
            processStatuses = AppController.getAllProcessStatus();
            employees = AppController.getAllEmployees();
            types = AppController.getAllTypes();

    } catch (DatabaseConnectionFailedException | GetAllCustomersException | GetAllSuppliersException | GetAllProcessStatusException | GetAllEmployeesException |
             GetAllProcessTypesException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        SearchByLabelPanel<Customer> customerSearch = new SearchByLabelPanel<>(customers, customer -> customer.getFirstName() + " " + customer.getLastName());
        customerSearch.getSearchField().setPlaceholder("Search for a customer");

        SearchByLabelPanel<Supplier> supplierSearch = new SearchByLabelPanel<>(suppliers, Supplier::getName);
        supplierSearch.getSearchField().setPlaceholder("Search for a supplier");

        SearchByLabelPanel<ProcessStatus> processStatusSearch = new SearchByLabelPanel<>(processStatuses, ProcessStatus::getLabel);
        processStatusSearch.getSearchField().setPlaceholder("Search for a process status");

        SearchByLabelPanel<Employee> employeeSearch = new SearchByLabelPanel<>(employees, employee -> employee.getFirstName() + " " + employee.getLastName());
        employeeSearch.getSearchField().setPlaceholder("Search for an employee");

        SearchByLabelPanel<ProcessType> typeSearch = new SearchByLabelPanel<>(types, ProcessType::getLabel);
        typeSearch.getSearchField().setPlaceholder("Search for a type");

        gridNewProcess.addField("Supplier", supplierSearch);
        gridNewProcess.addField("Process Status", processStatusSearch);
        gridNewProcess.addField("Type", typeSearch);
        gridNewProcess.addField("Employee", employeeSearch);
        gridNewProcess.addField("Customer", customerSearch);


        // Bttuon create
        JButton createButton = new JButton("Create Process");
        gridNewProcess.addField(createButton);

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

        add(gridNewProcess, BorderLayout.CENTER);

    }
}
