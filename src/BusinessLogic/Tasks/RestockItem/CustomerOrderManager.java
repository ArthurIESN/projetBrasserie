package BusinessLogic.Tasks.RestockItem;

import Model.Customer.Customer;
import Model.Employee.Employee;
import Model.Item.Item;
import Model.ProcessStatus.ProcessStatus;
import Model.ProcessType.ProcessType;

import java.util.HashMap;
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
        //@todo remove this
    }

}
