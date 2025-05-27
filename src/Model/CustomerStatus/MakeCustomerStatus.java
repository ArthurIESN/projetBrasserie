package Model.CustomerStatus;

import Exceptions.CustomerStatus.CustomerStatusException;

import java.util.HashMap;

public class MakeCustomerStatus
{
    private static final HashMap<Integer, CustomerStatus> customerStatusMap = new HashMap<>();

    public static CustomerStatus getCustomerStatus(Integer id, String label)
    {
        int customerStatusHash = CustomerStatus.hashCode(id, label);

        if(customerStatusMap.containsKey(customerStatusHash))
        {
            return customerStatusMap.get(customerStatusHash);
        }
        else
        {
            try
            {
                CustomerStatus customerStatus = new CustomerStatus(id, label);
                customerStatusMap.put(customerStatusHash, customerStatus);
                return customerStatus;
            }
            catch (CustomerStatusException e)
            {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
