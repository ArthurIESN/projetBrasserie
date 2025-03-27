package Model.Customer;

import Model.CustomerStatus.CustomerStatus;
import Model.CustomerStatus.MakeCustomerStatus;

import java.util.HashMap;

public class MakeCustomer
{
    private static final HashMap<Integer, Customer> customerMap = new HashMap<>();

    public static Customer getCustomer(Integer id, String lastName, String firstName, float creditLimit, String numVAT, Integer customerStatusId, String customerStatusLabel)
    {
        if(customerMap.containsKey(id))
        {
            return customerMap.get(id);
        }
        else
        {
            CustomerStatus customerStatus = MakeCustomerStatus.getCustomerStatus(customerStatusId, customerStatusLabel);
            Customer customer = new Customer(id, lastName, firstName, creditLimit, numVAT, customerStatus);
            customerMap.put(id, customer);
            return customer;
        }
    }
}
