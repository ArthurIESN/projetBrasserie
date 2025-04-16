package UI.Models.Document;
import Model.Document.Document;
import UI.Components.EnhancedTable.AbstractEnhancedTableModel;

import java.util.ArrayList;

public class SearchPaymentDocumentEnhancedTableModel extends AbstractEnhancedTableModel<Document>
{
    public SearchPaymentDocumentEnhancedTableModel(ArrayList<Document> data)
    {
        super("Document", new String[]{"ID", "Label", "Date"}, data);
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
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 2 -> java.util.Date.class;
            default -> String.class;
        };
    }
}
