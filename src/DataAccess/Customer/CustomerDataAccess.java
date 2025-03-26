package DataAccess.Customer;


import java.util.ArrayList;

import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;

import Model.Customer;

public interface CustomerDataAccess
{
    public ArrayList<Customer> getAllCustomers() throws DatabaseConnectionFailedException, GetAllCustomersException;
    public Customer getCustomer(int id);
    public void createCustomer(Customer customer);
    public void deleteCustomer(int id);
    public void updateCustomer(Customer customer);
}
