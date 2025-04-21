package Model.Customer;

import Model.CustomerStatus.CustomerStatus;

import java.util.HashMap;

public class MakeCustomer
{
    private static final HashMap<Integer, Customer> customerMap = new HashMap<>();

    public static Customer getCustomer(Integer id, String lastName, String firstName, float creditLimit, String numVAT, CustomerStatus customerStatus)
    {
        Customer customer = new Customer(id, lastName, firstName, creditLimit, numVAT, customerStatus);
        int customerHash = customer.hashCode();

        if(customerMap.containsKey(customerHash))
        {
            return customerMap.get(customerHash);
        }
        else
        {
            customerMap.put(customerHash, customer);
            return customer;
        }
    }
}
