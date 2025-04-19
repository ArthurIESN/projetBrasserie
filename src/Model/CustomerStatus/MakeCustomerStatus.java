package Model.CustomerStatus;

import java.util.HashMap;

public class MakeCustomerStatus
{
    private static final HashMap<Integer, CustomerStatus> customerStatusMap = new HashMap<>();

    public static CustomerStatus getCustomerStatus(Integer id, String label)
    {
        CustomerStatus customerStatus = new CustomerStatus(id, label);
        int customerStatusHash = customerStatus.hashCode();

        if(customerStatusMap.containsKey(customerStatusHash))
        {
            return customerStatusMap.get(customerStatusHash);
        }
        else
        {
            customerStatusMap.put(customerStatusHash, customerStatus);
            return customerStatus;
        }
    }
}
