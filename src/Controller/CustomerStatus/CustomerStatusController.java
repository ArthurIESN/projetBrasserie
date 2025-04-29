package Controller.CustomerStatus;

import BusinessLogic.CustomerStatus.CustomerStatusManager;
import Model.CustomerStatus.CustomerStatus;

import java.util.ArrayList;

public class CustomerStatusController
{
    private static final CustomerStatusManager customerStatusManager = new CustomerStatusManager();

    public static void createCustomerStatus(CustomerStatus customerStatus)
    {
        customerStatusManager.createCustomerStatus(customerStatus);
    }

    public static void updateCustomerStatus(CustomerStatus customerStatus)
    {
        customerStatusManager.updateCustomerStatus(customerStatus);
    }

    public static void deleteCustomerStatus(Integer id)
    {
        customerStatusManager.deleteCustomerStatus(id);
    }

    public static CustomerStatus getCustomerStatus(Integer id)
    {
        return customerStatusManager.getCustomerStatus(id);
    }

    public static ArrayList<CustomerStatus> getAllCustomerStatuses()
    {
        return customerStatusManager.getAllCustomerStatuses();
    }
}
