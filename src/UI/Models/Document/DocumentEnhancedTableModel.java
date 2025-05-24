package UI.Models.Document;

import Model.Document.Document;
import UI.Components.EnhancedTable.AbstractEnhancedTableModel;

import java.util.ArrayList;

public class DocumentEnhancedTableModel extends AbstractEnhancedTableModel<Document>
{
    public DocumentEnhancedTableModel(ArrayList<Document> data)
    {
        super("Document", new String[]{"ID", "Label", "Date", "Deadline", "Reduction", "Validity", "Is Delivered", "Delivery Date", "Deposit Is Paid", "Deposit Amount", "Desired Delivery Date",  "Total Inclusive Of Tax", "Total Vat", "Total Excl Vat"}, data);
    }

    public DocumentEnhancedTableModel()
    {
        this(new ArrayList<Document>());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Document document = getData().get(rowIndex);

        if (document == null)
        {
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> document.getId();
            case 1 -> document.getLabel();
            case 2 -> document.getDate();
            case 3 -> document.getDeadLine();
            case 4 -> document.getReduction();
            case 5 -> document.getValidity();
            case 6 -> document.getIsDelivered();
            case 7 -> document.getDeliveryDate();
            case 8 -> document.getDepositIsPaid();
            case 9 -> document.getDepositAmount();
            case 10 -> document.getDesiredDeliveryDate();
            case 11 -> document.getTotalInclusiveOfTax();
            case 12 -> document.getTotalVat();
            case 13 -> document.getTotalExclVat();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return switch (columnIndex) {
            case 0, 4, 8, 9, 11, 12, 13 -> Integer.class;
            case 2, 3, 7, 10 -> java.util.Date.class;
            default -> String.class;
        };
    }
}
