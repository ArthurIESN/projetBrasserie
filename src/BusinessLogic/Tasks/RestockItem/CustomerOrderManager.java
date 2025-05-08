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

        return totalPrice * MIN_DEPOSIT_PERCENTAGE;
    }

    public float[] calculateTaxes(HashMap<Item, Integer> items, Locality locality)
    {
        /*
        * 0 - Total Vat
        * 1 -  Total Delivery Cost
        * 2 -  Total VAT Inclusive
        * 3 - Total Price
         */
        float[] values = {0, 0, 0, 0};

        for (Map.Entry<Item, Integer> entry : items.entrySet())
        {
            int quantity = entry.getValue();
            Item item = entry.getKey();

            values[3] += item.getPrice() * quantity;
            values[0] += item.getPrice() * quantity*  (item.getVat().getRate() / 100);
        }

        values[1] = values[3] + values[0];

        if(locality != null)
        {
            values[2] = locality.getCountry().getDeliveryCost();
        }

        values[3] += values[2] + values[0];

        return values;
    }

    public void executeOrder(HashMap<Item, Integer> items, Customer customer, Employee employee, ProcessStatus processStatus, ProcessType processType, float[] values)
    {
        // create the proces

    }

}
