package DataAccess.Customer;


import java.util.ArrayList;

import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;

import Model.Customer;

public interface CustomerDataAccess
{
    ArrayList<Customer> getAllCustomers() throws DatabaseConnectionFailedException, GetAllCustomersException;
    Customer getCustomer(int id);
    void createCustomer(Customer customer);
    void deleteCustomer(int id);
    void updateCustomer(Customer customer);
}
