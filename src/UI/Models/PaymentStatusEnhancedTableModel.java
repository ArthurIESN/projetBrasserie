package UI.Models;

import Model.PaymentStatus.PaymentStatus;
import UI.Components.EnhancedTable.AbstractEnhancedTableModel;

import java.util.ArrayList;

public class PaymentStatusEnhancedTableModel extends AbstractEnhancedTableModel<PaymentStatus>
{

    public PaymentStatusEnhancedTableModel(ArrayList<PaymentStatus> data) {
        super("Payment Status", new String[]{"ID", "Label"}, data);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PaymentStatus paymentStatus = getData().get(rowIndex);

        if (paymentStatus == null) {
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> paymentStatus.getId();
            case 1 -> paymentStatus.getLabel();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            default -> String.class;
        };
    }
}
