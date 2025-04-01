package Model.Customer;

import Model.CustomerStatus.CustomerStatus;

import java.util.HashMap;

public class MakeCustomer
{
    private static final HashMap<Integer, Customer> customerMap = new HashMap<>();

    public static Customer getCustomer(Integer id, String lastName, String firstName, float creditLimit, String numVAT, CustomerStatus customerStatus)
    {
        if(customerMap.containsKey(id))
        {
            return customerMap.get(id);
        }
        else
        {
            Customer customer = new Customer(id, lastName, firstName, creditLimit, numVAT, customerStatus);
            customerMap.put(id, customer);
            return customer;
        }
    }
}
