package Controller.Customer;

import BusinessLogic.Customer.CustomerManager;
import Exceptions.Customer.GetAllCustomersException;
import Model.Customer.Customer;

import java.util.ArrayList;

public class CustomerController {
    private static final CustomerManager customerManager = new CustomerManager();

    public static ArrayList<Customer> getAllCustomers() throws GetAllCustomersException
    {
        return customerManager.getAllCustomers();
    }

    public static Customer getCustomer(int id)
    {
        return customerManager.getCustomer(id);
    }

    public static void createCustomer(Customer customer)
    {
        customerManager.createCustomer(customer);
    }

    public static void deleteCustomer(int id)
    {
        customerManager.deleteCustomer(id);
    }

    public static void updateCustomer(Customer customer)
    {
        customerManager.updateCustomer(customer);
    }
}
