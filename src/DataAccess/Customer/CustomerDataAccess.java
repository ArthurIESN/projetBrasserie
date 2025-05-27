package DataAccess.Customer;


import java.util.ArrayList;

import Exceptions.Customer.GetAllCustomersException;

import Model.Customer.Customer;

public interface CustomerDataAccess
{
    ArrayList<Customer> getAllCustomers() throws GetAllCustomersException;
    Customer getCustomer(int id);
    void createCustomer(Customer customer);
    void deleteCustomer(int id);
    void updateCustomer(Customer customer);
}
