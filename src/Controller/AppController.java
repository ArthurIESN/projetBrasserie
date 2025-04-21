package Controller;

import BusinessLogic.Customer.CustomerManager;
import BusinessLogic.DocumentStatus.DocumentStatusManager;
import BusinessLogic.Employee.EmployeeManager;
import BusinessLogic.Item.ItemManager;
import BusinessLogic.Process.ProcessManager;
import BusinessLogic.Supplier.SupplierManager;
import BusinessLogic.ProcessStatus.ProcessStatusManager;
import BusinessLogic.ProcessType.ProcessTypeManager;

import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Employee.GetAllEmployeesException;
import Exceptions.Item.GetAllItemsException;
import Exceptions.Process.CreateProcessException;
import Exceptions.Supplier.GetAllSuppliersException;
import Exceptions.ProcessType.GetAllProcessTypesException;
import Exceptions.ProcessStatus.GetAllProcessStatusException;
import Exceptions.Process.DeleteProcessException;
import Exceptions.Process.GetAllProcessesException;


import Exceptions.Vat.GetAllVatsException;
import Model.Customer.Customer;
import Model.DocumentStatus.DocumentStatus;
import Model.Employee.Employee;
import Model.Item.Item;
import Model.ProcessStatus.ProcessStatus;
import Model.Supplier.Supplier;
import Model.ProcessType.ProcessType;
import Model.Process.Process;
import Model.Vat.Vat;

import java.util.ArrayList;
import java.util.List;

public class AppController {
    private static final ItemManager itemManager = new ItemManager();
    private static final EmployeeManager employeeManager = new EmployeeManager();
    private static final SupplierManager supplierManager = new SupplierManager();
    private static final CustomerManager customerManager = new CustomerManager();
    private static final ProcessStatusManager processStatusManager = new ProcessStatusManager();
    private static final ProcessTypeManager typeManager = new ProcessTypeManager();
    private static final ProcessManager processManager = new ProcessManager();
    private static final DocumentStatusManager documentStatusManager = new DocumentStatusManager();
    private static final VatManager vatManager = new VatManager();

    public static List<Item> getAllItems() throws GetAllItemsException
    {
        return itemManager.getAllItems();
    }

    // employee
    public static ArrayList<Employee> getAllEmployees() throws GetAllEmployeesException
    {
        return employeeManager.getAllEmployees();
    }

    // supplier
    public static ArrayList<Supplier> getAllSuppliers() throws GetAllSuppliersException
    {
        return supplierManager.getAllSuppliers();
    }

    // customer
    public static ArrayList<Customer> getAllCustomers() throws GetAllCustomersException
    {
        return customerManager.getAllCustomers();
    }

    // process status
    public static ArrayList<ProcessStatus> getAllProcessStatus() throws GetAllProcessStatusException
    {
        return processStatusManager.getAllProcessStatus();
    }

    // Type
    public static ArrayList<ProcessType> getAllTypes() throws GetAllProcessTypesException
    {
        return typeManager.getAllTypes();
    }

    // VAT
    public static ArrayList<Vat> getAllVats() throws GetAllVatsException
    {
        return vatManager.getAllVats();
    }

    // process
    public static ArrayList<Process> getAllProcesses() throws GetAllProcessesException
    {
        return processManager.getAllProcessess();
    }

    public static void createProcess(Process process) throws CreateProcessException
    {
        processManager.createProcess(process);
    }

    public static void deleteProcess(Integer id) throws DeleteProcessException
    {
        processManager.deleteProcess(id);
    }

    public static ArrayList<DocumentStatus> getAllDocumentStatus() throws DatabaseConnectionFailedException,
            Exceptions.DocumentStatus.GetAllDocumentStatusException{
        return documentStatusManager.getAllDocumentStatus();
    }
}
