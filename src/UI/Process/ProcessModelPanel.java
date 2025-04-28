package UI.Process;

import Controller.Customer.CustomerController;
import Controller.Employee.EmployeeController;
import Controller.Process.ProcessController;
import Controller.ProcessStatus.ProcessStatusController;
import Controller.Supplier.SupplierController;
import Controller.Type.TypeController;
import Exceptions.Customer.GetAllCustomersException;
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

import UI.Components.Fields.JNumberField;
import UI.Components.GridBagLayoutHelper;
import UI.Components.Fields.JDateField;
import UI.Components.Fields.JEnhancedTextField;
import UI.Components.Fields.SearchListPanel;
import UI.Components.StepManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ProcessModelPanel extends JPanel
{
    private GridBagLayoutHelper gridProcess;
    private JButton button;
    private SearchListPanel<Process> processSearch;
    private JEnhancedTextField IdField;
    private JNumberField processIdField;
    private JEnhancedTextField processLabelField;
    private JNumberField processNumberField;
    private SearchListPanel<Customer> customerSearch;
    private SearchListPanel<Supplier> supplierSearch;
    private SearchListPanel<ProcessStatus> processStatusSearch;
    private SearchListPanel<Employee> employeeSearch;
    private SearchListPanel<ProcessType> typeSearch;
    public ProcessModelPanel(boolean updateProcess)
    {
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        ArrayList<ProcessStatus> processStatuses = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<ProcessType> types = new ArrayList<>();
        ArrayList<Process> processes = new ArrayList<>();

        try
        {


            if(updateProcess)
            {
                processes = ProcessController.getAllProcesses();
            }

            customers = CustomerController.getAllCustomers();
            suppliers = SupplierController.getAllSuppliers();
            processStatuses = ProcessStatusController.getAllProcessStatus();
            employees = EmployeeController.getAllEmployees();
            types = TypeController.getAllTypes();
        } catch (GetAllCustomersException | GetAllSuppliersException |
                 GetAllProcessStatusException | GetAllEmployeesException | GetAllProcessesException |
                 GetAllProcessTypesException e)
        {
            JOptionPane.showMessageDialog(null, "Failed to get customers, suppliers, process statuses, employees or processes. You are unable to create a process.", "Error", JOptionPane.ERROR_MESSAGE);
        }


        setLayout(new BorderLayout());


        gridProcess = new GridBagLayoutHelper();

        if(updateProcess)
        {
            processSearch = new SearchListPanel<>(processes, searchProcess -> searchProcess.getLabel() + " - " + searchProcess.getNumber() + " - " + searchProcess.getProcessStatus().getLabel());
            processSearch.getSearchField().setPlaceholder("Search for a process");

            processSearch.onSelectedItemChange(e ->
            {
                int i = 1;
                while(i < gridProcess.getComponents().length)
                {
                    gridProcess.getComponents()[i].setVisible(true);
                    i++;
                }
            });

            processIdField = new JNumberField();
            processIdField.setPlaceholder("Process Id");
            processIdField.setCanClear(false);
            processIdField.setEnabled(false);

            gridProcess.addField("Search for a Process", processSearch);
            gridProcess.addField("Process ID", processIdField);

        }

        processLabelField = new JEnhancedTextField();
        processLabelField.setPlaceholder("Process Label");

        processNumberField = new JNumberField();
        processNumberField.setPlaceholder("Process Number");

        customerSearch = new SearchListPanel<>(customers, customer -> customer != null ? customer.getFirstName() + " " + customer.getLastName() : "");
        customerSearch.getSearchField().setPlaceholder("Search for a customer");

        supplierSearch = new SearchListPanel<>(suppliers, supplier -> supplier != null ? supplier.getName() : "");
        supplierSearch.getSearchField().setPlaceholder("Search for a supplier");

        processStatusSearch = new SearchListPanel<>(processStatuses, ProcessStatus::getLabel);
        processStatusSearch.getSearchField().setPlaceholder("Search for a process status");

        employeeSearch = new SearchListPanel<>(employees, employee -> employee != null ? employee.getFirstName() + " " + employee.getLastName() : "");
        employeeSearch.getSearchField().setPlaceholder("Search for an employee");

        typeSearch = new SearchListPanel<>(types, ProcessType::getLabel);
        typeSearch.getSearchField().setPlaceholder("Search for a type");

        button = new JButton();

        gridProcess.addField("Process Label *", processLabelField);
        gridProcess.addField("Process Number *", processNumberField);
        gridProcess.addField("Select a Process Status *", processStatusSearch);
        gridProcess.addField("Select a Process Type *", typeSearch);
        gridProcess.addField("Select a Supplier ",supplierSearch);
        gridProcess.addField("Select an Employee", employeeSearch);
        gridProcess.addField("Select a Customer", customerSearch);
        gridProcess.addField(button);


        if(updateProcess)
        {
            int i = 1; // We dont want to hide the first component
            while(i < gridProcess.getComponents().length)
            {
                gridProcess.getComponents()[i].setVisible(false);
                i++;
            }
        }



        add(gridProcess, BorderLayout.CENTER);
    }

    public boolean isProcessInvalid()
    {
        if(processLabelField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please fill in the process label", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if(processNumberField.getInt() <= 0)
        {
            JOptionPane.showMessageDialog(this, "Please fill in the process number. The process number must be greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if(processStatusSearch.getSelectedItem() == null)
        {
            JOptionPane.showMessageDialog(this, "Please select a process status", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if(typeSearch.getSelectedItem() == null)
        {
            JOptionPane.showMessageDialog(this, "Please select a process type", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
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

    public JNumberField getProcessIdField()
    {
        return processIdField;
    }

    public JEnhancedTextField getProcessLabelField()
    {
        return processLabelField;
    }

    public JNumberField getProcessNumberField()
    {
        return processNumberField;
    }

    public SearchListPanel<Customer> getCustomerSearch()
    {
        return customerSearch;
    }

    public SearchListPanel<Supplier> getSupplierSearch()
    {
        return supplierSearch;
    }

    public SearchListPanel<ProcessStatus> getProcessStatusSearch()
    {
        return processStatusSearch;
    }

    public SearchListPanel<Employee> getEmployeeSearch()
    {
        return employeeSearch;
    }

    public SearchListPanel<ProcessType> getTypeSearch()
    {
        return typeSearch;
    }

    public SearchListPanel<Process> getProcessSearch()
    {
        return processSearch;
    }
}
