package BusinessLogic.Tasks.RestockItem;

import Controller.AppController;
import Controller.Process.ProcessController;
import Controller.ProcessStatus.ProcessStatusController;
import Controller.ProcessType.ProcessTypeController;
import Exceptions.ProcessStatus.GetProcessStatusException;
import Exceptions.ProcessType.GetAllProcessTypesException;
import Exceptions.ProcessType.GetProcessTypeException;
import Model.Customer.Customer;
import Model.Employee.Employee;
import Model.Item.Item;
import Model.Process.Process;
import Model.Locality.Locality;
import Model.ProcessStatus.ProcessStatus;
import Model.ProcessType.ProcessType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomerOrderManager
{
    private static final float MIN_DEPOSIT_PERCENTAGE = 0.2f;

    public float customerDepositMinimumAmount(Customer customer, float totalPrice)
    {
        if(Objects.equals(customer.getCustomerStatus().getLabel(), "VIP")) return 0.f;

        return Math.round((totalPrice * MIN_DEPOSIT_PERCENTAGE) * 100f) / 100f;
    }



    public void executeOrder(HashMap<Item, Integer> items, Customer customer, Employee employee, ProcessStatus processStatus, ProcessType processType, float[] values)
    {
        // create the proces

    }

}
