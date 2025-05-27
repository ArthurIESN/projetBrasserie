package BusinessLogic.Tasks.RestockItem;

import Model.Customer.Customer;
import Model.Employee.Employee;
import Model.Item.Item;
import Model.ProcessStatus.ProcessStatus;
import Model.ProcessType.ProcessType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

    public Date getDate(int days)
    {
        Date currentDate = new Date();
        LocalDate localDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate deliveryLocalDate = localDate.plusDays(days);
        return Date.from(deliveryLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
