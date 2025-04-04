package UI.Test;

import Model.Customer.Customer;
import UI.Components.AbstractEnhancedTableModel;

import java.util.ArrayList;

public class CustomerEnhancedTableModel extends AbstractEnhancedTableModel<Customer>
{

    public CustomerEnhancedTableModel(ArrayList<Customer> customers)
    {
        super("Customer", new String[]{"ID", "Last Name", "First Name", "Credit Limit", "VAT Number"}, customers);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Customer customer = getData().get(rowIndex);

        if(customer == null)
        {
            return " - ";
        }

        return switch (columnIndex)
        {
            case 0 -> customer.getId();
            case 1 -> customer.getLastName();
            case 2 -> customer.getFirstName();
            case 3 -> customer.getCreditLimit();
            case 4 -> customer.getNumVAT();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 3 -> Double.class;
            default -> String.class;
        };
    }
}
