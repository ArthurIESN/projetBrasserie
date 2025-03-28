package Model.CustomerStatus;

import java.util.HashMap;

public class MakeCustomerStatus
{
    private static final HashMap<Integer, CustomerStatus> customerStatusMap = new HashMap<>();

    public static CustomerStatus getCustomerStatus(Integer id, String label)
    {
        if(customerStatusMap.containsKey(id))
        {
            return customerStatusMap.get(id);
        }
        else
        {
            CustomerStatus customerStatus = new CustomerStatus(id, label);
            customerStatusMap.put(id, customerStatus);
            return customerStatus;
        }
    }
}
