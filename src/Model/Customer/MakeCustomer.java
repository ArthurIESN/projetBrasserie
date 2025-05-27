package Model.Customer;

import Exceptions.Customer.CustomerException;
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
            try
            {
                Customer customer = new Customer(id, lastName, firstName, creditLimit, numVAT, customerStatus);
                customerMap.put(customerHash, customer);
                return customer;
            }
            catch (CustomerException e)
            {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
