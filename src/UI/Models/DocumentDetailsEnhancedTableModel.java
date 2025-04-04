package UI.Models;

import UI.Components.AbstractEnhancedTableModel;


import java.util.ArrayList;
import Model.DocumentDetails.DocumentDetails;

public class DocumentDetailsEnhancedTableModel extends AbstractEnhancedTableModel<DocumentDetails>
{
    public DocumentDetailsEnhancedTableModel(ArrayList<DocumentDetails> data)
    {
        super("Document Details", new String[]{"ID", "Label"}, data);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        DocumentDetails documentDetails = getData().get(rowIndex);

        if (documentDetails == null)
        {
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> documentDetails.getId();
            case 1 -> documentDetails.getLabel();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            default -> String.class;
        };
    }

}
