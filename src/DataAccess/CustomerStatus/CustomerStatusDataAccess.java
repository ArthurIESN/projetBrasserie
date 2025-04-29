package DataAccess.CustomerStatus;

import Model.CustomerStatus.CustomerStatus;

import java.util.ArrayList;

public interface CustomerStatusDataAccess
{
    void createCustomerStatus(CustomerStatus customerStatus);
    void updateCustomerStatus(CustomerStatus customerStatus);
    void deleteCustomerStatus(Integer id);
    CustomerStatus getCustomerStatus(Integer id);
    ArrayList<CustomerStatus> getAllCustomerStatuses();
}
