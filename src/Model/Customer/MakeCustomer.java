package Model.Customer;

import Model.CustomerStatus.CustomerStatus;

import java.util.HashMap;

public class MakeCustomer
{
    private static final HashMap<Integer, Customer> customerMap = new HashMap<>();

    public static Customer getCustomer(Integer id, String lastName, String firstName, float creditLimit, String numVAT, CustomerStatus customerStatus)
    {
        int customerHash = Customer.hashCode(id, lastName, firstName, creditLimit, numVAT, customerStatus);

        if(customerMap.containsKey(customerHash))
        {
            return customerMap.get(customerHash);
        }
        else
        {
            Customer customer = new Customer(id, lastName, firstName, creditLimit, numVAT, customerStatus);
            customerMap.put(customerHash, customer);
            return customer;
        }
    }
}
