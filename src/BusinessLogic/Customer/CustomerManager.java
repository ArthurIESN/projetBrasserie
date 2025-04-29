package BusinessLogic.Customer;

import DataAccess.Customer.CustomerDBAccess;
import DataAccess.Customer.CustomerDataAccess;
import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Model.Customer.Customer;

import java.util.ArrayList;

public class CustomerManager
{
    private final CustomerDataAccess customerDataAccess = new CustomerDBAccess();

    public ArrayList<Customer> getAllCustomers() throws GetAllCustomersException
    {
        return customerDataAccess.getAllCustomers();
    }

    public Customer getCustomer(int id)
    {
        return customerDataAccess.getCustomer(id);
    }

    public void createCustomer(Customer customer)
    {
        customerDataAccess.createCustomer(customer);
    }

    public void deleteCustomer(int id)
    {
        customerDataAccess.deleteCustomer(id);
    }

    public void updateCustomer(Customer customer)
    {
        customerDataAccess.updateCustomer(customer);
    }
}
