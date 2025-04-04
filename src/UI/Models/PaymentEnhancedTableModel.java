package UI.Models;

import Model.Payment.Payment;
import UI.Components.AbstractEnhancedTableModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class PaymentEnhancedTableModel extends AbstractEnhancedTableModel<Payment>
{
    public PaymentEnhancedTableModel(ArrayList<Payment> data) {
        super("Payments", new String[]{"ID", "Amount", "Payment Date"}, data);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Payment payment = getData().get(rowIndex);

        if (payment == null) {
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> payment.getId();
            case 1 -> payment.getAmount();
            case 2 -> payment.getPaymentDate();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 1 -> Double.class;
            case 2 -> Date.class;
            default -> String.class;
        };
    }
}
