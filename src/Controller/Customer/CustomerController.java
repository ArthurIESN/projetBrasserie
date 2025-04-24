package Controller.Customer;

import BusinessLogic.Customer.CustomerManager;
import Exceptions.Customer.GetAllCustomersException;
import Model.Customer.Customer;

import java.util.ArrayList;

public class CustomerController {
    private static final CustomerManager customerManager = new CustomerManager();

    // customer
    public static ArrayList<Customer> getAllCustomers() throws GetAllCustomersException
    {
        return customerManager.getAllCustomers();
    }
}
