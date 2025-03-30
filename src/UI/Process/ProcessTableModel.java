package UI.Process;

import Model.Process.Process;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProcessTableModel extends AbstractTableModel
{
    private final ArrayList<Process> items;
    private final String[] columnNames = {
            "ID", "Label", "Number", "Supplier ID", "Supplier Name",
            "Type ID", "Type Label", "Process Status ID", "Process Status Label",
            "Employee ID", "Employee Last Name", "Employee First Name", "Birth Date",
            "Employee Status ID", "Employee Status Label", "Customer ID",
            "Customer Last Name", "Customer First Name", "Customer Credit Limit",
            "Customer VAT Number", "Customer Status ID", "Customer Status Label"
    };

    public ProcessTableModel(ArrayList<Process> processes) {
        this.items = processes;
    }
    public ProcessTableModel(Process process)
    {
        this.items = new ArrayList<>();
        this.items.add(process);
    }
    public ProcessTableModel()
    {
        this.items = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class getColumnClass(int column)
    {
        Class c;

        switch (column)
        {
            case 0, 2, 3, 5, 7, 9, 15, 20, 13, 21 -> c = Integer.class;
            case 1, 4, 6, 8, 10, 11, 16, 17 -> c = String.class;
            case 12, 18 -> c = java.sql.Date.class;
            default -> c = String.class;
        }

        return c;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Process process = items.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> process.getId();
            case 1 -> process.getLabel();
            case 2 -> process.getNumber();
            case 3 -> process.getSupplier() != null ? process.getSupplier().getId() : "-";
            case 4 -> process.getSupplier() != null ? process.getSupplier().getName() : "-";
            case 5 -> process.getType().getId();
            case 6 -> process.getType().getLabel();
            case 7 -> process.getProcessStatus().getId();
            case 8 -> process.getProcessStatus().getLabel();
            case 9 -> process.getEmployee() != null ? process.getEmployee().getId() : "-";
            case 10 -> process.getEmployee() != null ? process.getEmployee().getLastName() : "-";
            case 11 -> process.getEmployee() != null ? process.getEmployee().getFirstName() : "-";
            case 12 -> process.getEmployee() != null ? process.getEmployee().getBirthDate() : "-";
            case 13 -> process.getEmployee() != null ? process.getEmployee().getEmployeeStatus().getId() : "-";
            case 14 -> process.getEmployee() != null ? process.getEmployee().getEmployeeStatus().getLabel() : "-";
            case 15 -> process.getCustomer() != null ? process.getCustomer().getId() : "-";
            case 16 -> process.getCustomer() != null ? process.getCustomer().getLastName() : "-";
            case 17 -> process.getCustomer() != null ? process.getCustomer().getFirstName() : "-";
            case 18 -> process.getCustomer() != null ? process.getCustomer().getCreditLimit() : "-";
            case 19 -> process.getCustomer() != null ? process.getCustomer().getNumVAT() : "-";
            case 20 -> process.getCustomer() != null ? process.getCustomer().getCustomerStatus().getId() : "-";
            case 21 -> process.getCustomer() != null ? process.getCustomer().getCustomerStatus().getLabel() : "-";
            default -> null;
        };
    }
}