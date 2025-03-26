package UI.Process;

import DataAccess.Customer.CustomerDBAccess;
import DataAccess.Process.ProcessDBAccess;
import DataAccess.ProcessStatus.ProcessStatusDBAccess;
import DataAccess.Supplier.SupplierDBAccess;
import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DataAccess.Process.GetAllProcessesException;
import Exceptions.ProcessStatus.GetAllProcessStatusException;
import Exceptions.Supplier.GetAllSuppliersException;
import Model.Customer;
import Model.Process;
import Model.ProcessStatus;
import Model.Supplier;
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
        SearchByLabelPanel<Customer> customerSearch;
        try
        {
            CustomerDBAccess customerDBAccess = new CustomerDBAccess();
            ArrayList<Customer> customers = customerDBAccess.getAllCustomers();

            customerSearch = new SearchByLabelPanel<>(customers, customer -> customer.getLastName() + " - " + customer.getFirstName());
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

            supplierSearch = new SearchByLabelPanel<>(suppliers, Supplier::getName);
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

            processStatusSearch = new SearchByLabelPanel<>(processStatuses, ProcessStatus::getLabel);
            gridNewProcess.addField("Process Status", processStatusSearch);
        } catch (DatabaseConnectionFailedException | GetAllProcessStatusException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        add(searchForm, BorderLayout.CENTER);

    }
}
