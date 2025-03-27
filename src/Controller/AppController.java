package Controller;

import BusinessLogic.Customer.CustomerManager;
import BusinessLogic.Employee.EmployeeManager;
import BusinessLogic.Item.ItemManager;
import BusinessLogic.Process.ProcessManager;
import BusinessLogic.Supplier.SupplierManager;
import BusinessLogic.ProcessStatus.ProcessStatusManager;
import BusinessLogic.Type.TypeManager;

import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Employee.GetAllEmployeesException;
import Exceptions.Supplier.GetAllSuppliersException;
import Exceptions.Type.GetAllTypesException;

import Model.Customer.Customer;
import Model.Employee.Employee;
import Model.Item.Item;
import Model.ProcessStatus.ProcessStatus;
import Model.Supplier.Supplier;
import Model.Type.Type;
import Model.Process.Process;

import java.util.ArrayList;
import java.util.List;

public class AppController {
    private static final ItemManager itemManager = new ItemManager();
    private static final EmployeeManager employeeManager = new EmployeeManager();
    private static final SupplierManager supplierManager = new SupplierManager();
    private static final CustomerManager customerManager = new CustomerManager();
    private static final ProcessStatusManager processStatusManager = new ProcessStatusManager();
    private static final TypeManager typeManager = new TypeManager();
    private static final ProcessManager processManager = new ProcessManager();


    public static List<Item> getAllItems() throws DatabaseConnectionFailedException
    {
        return itemManager.getAllItems();
    }

    // employee
    public static ArrayList<Employee> getAllEmployees() throws DatabaseConnectionFailedException, GetAllEmployeesException
    {
        return employeeManager.getAllEmployees();
    }

    // supplier
    public static ArrayList<Supplier> getAllSuppliers() throws DatabaseConnectionFailedException, GetAllSuppliersException
    {
        return supplierManager.getAllSuppliers();
    }

    // customer
    public static ArrayList<Customer> getAllCustomers() throws DatabaseConnectionFailedException, GetAllCustomersException
    {
        return customerManager.getAllCustomers();
    }

    // process status
    public static ArrayList<ProcessStatus> getAllProcessStatus() throws DatabaseConnectionFailedException, Exceptions.ProcessStatus.GetAllProcessStatusException
    {
        return processStatusManager.getAllProcessStatus();
    }

    // Type
    public static ArrayList<Type> getAllTypes() throws DatabaseConnectionFailedException, GetAllTypesException
    {
        return typeManager.getAllTypes();
    }

    // process
    public static ArrayList<Process> getAllProcesses() throws DatabaseConnectionFailedException, Exceptions.Process.GetAllProcessesException
    {
        return processManager.getAllProcessess();
    }
}
