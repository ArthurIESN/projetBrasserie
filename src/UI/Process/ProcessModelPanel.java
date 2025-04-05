package UI.Process;

import Controller.AppController;
import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Employee.GetAllEmployeesException;
import Exceptions.Process.GetAllProcessesException;
import Exceptions.ProcessStatus.GetAllProcessStatusException;
import Exceptions.ProcessType.GetAllProcessTypesException;
import Exceptions.Supplier.GetAllSuppliersException;

import Model.Process.Process;
import Model.Customer.Customer;
import Model.Employee.Employee;
import Model.ProcessStatus.ProcessStatus;
import Model.ProcessType.ProcessType;
import Model.Supplier.Supplier;

import UI.Components.GridBagLayoutHelper;
import UI.Components.Fields.JDate;
import UI.Components.Fields.JEnhancedTextField;
import UI.Components.Fields.SearchByLabelPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ProcessModelPanel extends JPanel
{
    private JButton button;
    private SearchByLabelPanel<Process> processSearch;
    private JEnhancedTextField IdField;
    private JEnhancedTextField processIdField;
    private JEnhancedTextField processLabelField;
    private JEnhancedTextField processNumberField;
    private JDate dateField;
    private SearchByLabelPanel<Customer> customerSearch;
    private SearchByLabelPanel<Supplier> supplierSearch;
    private SearchByLabelPanel<ProcessStatus> processStatusSearch;
    private SearchByLabelPanel<Employee> employeeSearch;
    private SearchByLabelPanel<ProcessType> typeSearch;
    public ProcessModelPanel(Process process, boolean showId, boolean showProcesses)
    {
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        ArrayList<ProcessStatus> processStatuses = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<ProcessType> types = new ArrayList<>();
        ArrayList<Process> processes = new ArrayList<>();

        try
        {
            customers = AppController.getAllCustomers();
            suppliers = AppController.getAllSuppliers();
            processStatuses = AppController.getAllProcessStatus();
            employees = AppController.getAllEmployees();
            types = AppController.getAllTypes();

            if(showProcesses)
            {
                processes = AppController.getAllProcesses();
            }
        } catch (DatabaseConnectionFailedException | GetAllCustomersException | GetAllSuppliersException |
                 GetAllProcessStatusException | GetAllEmployeesException | GetAllProcessesException |
                 GetAllProcessTypesException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


        GridBagLayoutHelper gridNewProcess = new GridBagLayoutHelper();

        if(showProcesses)
        {
            processSearch = new SearchByLabelPanel<>(processes, searchProcess -> searchProcess.getLabel() + " - " + searchProcess.getNumber() + " - " + searchProcess.getProcessStatus().getLabel());
            processSearch.getSearchField().setPlaceholder("Search for a process");

            gridNewProcess.addField("Process", processSearch);
        }

        if(showId)
        {
            processIdField = new JEnhancedTextField();
            processIdField.setPlaceholder("Process Id");
            processIdField.setEnabled(false);

            gridNewProcess.addField("Process Id", processIdField);
        }

        processLabelField = new JEnhancedTextField();
        processLabelField.setPlaceholder("Process Label");

        processNumberField = new JEnhancedTextField();
        processNumberField.setPlaceholder("Process Number");

        dateField = new JDate();
        dateField.setPlaceholder("Creation Date");
        dateField.setMaxDate(new Date());
        // 2000 January 1st is the minimum date for the date field
        dateField.setMinDate(new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime());


        customerSearch = new SearchByLabelPanel<>(customers, customer -> customer != null ? customer.getFirstName() + " " + customer.getLastName() : "");
        customerSearch.getSearchField().setPlaceholder("Search for a customer");

        supplierSearch = new SearchByLabelPanel<>(suppliers, Supplier::getName);
        supplierSearch.getSearchField().setPlaceholder("Search for a supplier");

        processStatusSearch = new SearchByLabelPanel<>(processStatuses, ProcessStatus::getLabel);
        processStatusSearch.getSearchField().setPlaceholder("Search for a process status");

        employeeSearch = new SearchByLabelPanel<>(employees, employee -> employee != null ? employee.getFirstName() + " " + employee.getLastName() : "");
        employeeSearch.getSearchField().setPlaceholder("Search for an employee");

        typeSearch = new SearchByLabelPanel<>(types, ProcessType::getLabel);
        typeSearch.getSearchField().setPlaceholder("Search for a type");

        button = new JButton();

        gridNewProcess.addField("Process Label", processLabelField);
        gridNewProcess.addField("Process Number", processNumberField);
        gridNewProcess.addField("Creation Date", dateField);
        gridNewProcess.addField("Supplier", supplierSearch);
        gridNewProcess.addField("Process Status", processStatusSearch);
        gridNewProcess.addField("Type", typeSearch);
        gridNewProcess.addField("Employee", employeeSearch);
        gridNewProcess.addField("Customer", customerSearch);
        gridNewProcess.addField(button);

        add(gridNewProcess, BorderLayout.CENTER);
    }

    public ProcessModelPanel( boolean showId, boolean showProcesses)
    {
        this(null, showId, showProcesses);
    }

    public void onButtonClicked(ActionListener actionListener)
    {
        button.addActionListener(actionListener);
    }

    public void onSearchProcessChange(ActionListener actionListener)
    {
        processSearch.onSelectedItemChange(actionListener);
    }

    public void setButtonText(String text)
    {
        button.setText(text);
    }

    public JEnhancedTextField getProcessIdField()
    {
        return processIdField;
    }

    public JEnhancedTextField getProcessLabelField()
    {
        return processLabelField;
    }

    public JEnhancedTextField getProcessNumberField()
    {
        return processNumberField;
    }

    public JDate getDateField()
    {
        return dateField;
    }

    public SearchByLabelPanel<Customer> getCustomerSearch()
    {
        return customerSearch;
    }

    public SearchByLabelPanel<Supplier> getSupplierSearch()
    {
        return supplierSearch;
    }

    public SearchByLabelPanel<ProcessStatus> getProcessStatusSearch()
    {
        return processStatusSearch;
    }

    public SearchByLabelPanel<Employee> getEmployeeSearch()
    {
        return employeeSearch;
    }

    public SearchByLabelPanel<ProcessType> getTypeSearch()
    {
        return typeSearch;
    }

    public SearchByLabelPanel<Process> getProcessSearch()
    {
        return processSearch;
    }
}
