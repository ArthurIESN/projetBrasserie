package UI.Search.Payment;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import Model.Payment.Payment;

public class PaymentTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Amount", "Payment date", "Status","Document id", "Document label",
             "Document date","Process id", "process label", "Process id type", "Customer NB", "Customer last name",
            "Customer first name", "Customer Credit limit", "Customer VAT number"};

    private final ArrayList<Payment> payments;

    public PaymentTableModel(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public PaymentTableModel() {
        this(new ArrayList<>());
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
        return switch (iColumn) {
            case 0 -> payment.getId();
            case 1 -> payment.getAmount();
            case 2 -> payment.getPaymentDate();
            case 3 -> payment.getPaymentStatus().getLabel();
            case 4 -> payment.getDocument() != null ? payment.getDocument().getId() : null;
            case 5 -> payment.getDocument() != null ? payment.getDocument().getLabel() : null;
            case 6 -> payment.getDocument() != null ? payment.getDocument().getDate() : null;
            case 7 -> payment.getProcess() != null ? payment.getProcess().getId() : null;
            case 8 -> payment.getProcess() != null ? payment.getProcess().getLabel() : null;
            case 9 ->payment.getProcess() != null && payment.getProcess().getType() != null ? payment.getProcess().getType().getId() : null;
            case 10, 11 -> payment.getCustomer() != null ? payment.getCustomer().getId() : null;
            case 12 -> payment.getCustomer() != null ? payment.getCustomer().getLastName() : null;
            case 13 -> payment.getCustomer() != null ? payment.getCustomer().getFirstName() : null;
            case 14 -> payment.getCustomer() != null ? payment.getCustomer().getCreditLimit() : null;
            case 15 -> payment.getCustomer() != null ? payment.getCustomer().getNumVAT() : null;
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}