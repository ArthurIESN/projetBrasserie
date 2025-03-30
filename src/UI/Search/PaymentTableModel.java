package UI.Search;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import Model.Payment.Payment;

public class PaymentTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Amount", "Date", "Status"};
    private final ArrayList<Payment> payments;

    public PaymentTableModel(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public int getRowCount() {
        return payments.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int i, int iColumn) {
        Payment payment = payments.get(i);
        switch (iColumn) {
            case 0:
                return payment.getId();
            case 1:
                return payment.getAmount();
            case 2:
                return payment.getPaymentDate();
            case 3:
                return payment.getPaymentStatus();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}