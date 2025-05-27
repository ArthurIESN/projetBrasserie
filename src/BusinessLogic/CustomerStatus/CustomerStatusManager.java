package BusinessLogic.CustomerStatus;

import DataAccess.CustomerStatus.CustomerStatusDBAccess;
import DataAccess.CustomerStatus.CustomerStatusDataAccess;
import Model.CustomerStatus.CustomerStatus;

import java.util.ArrayList;

public class CustomerStatusManager
{
    private final CustomerStatusDataAccess customerStatusDataAccess = new CustomerStatusDBAccess();

    public void createCustomerStatus(CustomerStatus customerStatus)
    {
        customerStatusDataAccess.createCustomerStatus(customerStatus);
    }

    public void updateCustomerStatus(CustomerStatus customerStatus)
    {
        customerStatusDataAccess.updateCustomerStatus(customerStatus);
    }

    public void deleteCustomerStatus(Integer id)
    {
        customerStatusDataAccess.deleteCustomerStatus(id);
    }

    public CustomerStatus getCustomerStatus(Integer id)
    {
        return customerStatusDataAccess.getCustomerStatus(id);
    }

    public ArrayList<CustomerStatus> getAllCustomerStatuses()
    {
        return customerStatusDataAccess.getAllCustomerStatuses();
    }
}
