package UI.Search;

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
        switch (iColumn) {
            case 0:
                return payment.getId();
            case 1:
                return payment.getAmount();
            case 2:
                return payment.getPaymentDate();
            case 3:
                return payment.getPaymentStatus().getLabel();
            case 4:
                return payment.getDocument() != null ? payment.getDocument().getId() : null;
            case 5:
                return payment.getDocument() != null ? payment.getDocument().getLabel() : null;
            case 6:
                return payment.getDocument() != null ? payment.getDocument().getDate() : null;
            case 7:
                return payment.getProcess() != null ? payment.getProcess().getId() : null;
            case 8:
                return payment.getProcess() != null ? payment.getProcess().getLabel() : null;
            case 9:
                return payment.getProcess() != null && payment.getProcess().getType() != null ? payment.getProcess().getType().getId() : null;
            case 10:
                return payment.getCustomer() != null ? payment.getCustomer().getId() : null;
            case 11:
                return payment.getCustomer() != null ? payment.getCustomer().getLastName() : null;
            case 12:
                return payment.getCustomer() != null ? payment.getCustomer().getFirstName() : null;
            case 13:
                return payment.getCustomer() != null ? payment.getCustomer().getCreditLimit() : null;
            case 14:
                return payment.getCustomer() != null ? payment.getCustomer().getNumVAT() : null;
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}