package BusinessLogic.Customer;

import DataAccess.Customer.CustomerDBAccess;
import DataAccess.Customer.CustomerDataAccess;
import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Model.Customer.Customer;

import java.util.ArrayList;

public class CustomerManager
{
    private final CustomerDataAccess customerDataAccess;

    public CustomerManager()
    {
        customerDataAccess = new CustomerDBAccess();
    }

    public ArrayList<Customer> getAllCustomers() throws GetAllCustomersException
    {
        return customerDataAccess.getAllCustomers();
    }


}
