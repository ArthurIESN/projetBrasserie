package Controller;

import BusinessLogic.Customer.CustomerManager;
import BusinessLogic.Employee.EmployeeManager;
import BusinessLogic.Item.ItemManager;
import BusinessLogic.Supplier.SupplierManager;
import BusinessLogic.ProcessStatus.ProcessStatusManager;

import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Employee.GetAllEmployeesException;
import Exceptions.Supplier.GetAllSuppliersException;

import Model.Customer;
import Model.Employee;
import Model.Item.Item;
import Model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class AppController {
    private static final ItemManager itemManager = new ItemManager();
    private static final EmployeeManager employeeManager = new EmployeeManager();
    private static final SupplierManager supplierManager = new SupplierManager();
    private static final CustomerManager customerManager = new CustomerManager();
    private static final ProcessStatusManager processStatusManager = new ProcessStatusManager();

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
    public static ArrayList<Model.ProcessStatus> getAllProcessStatus() throws DatabaseConnectionFailedException, Exceptions.ProcessStatus.GetAllProcessStatusException
    {
        return processStatusManager.getAllProcessStatus();
    }
}
