package UI.Models;

import Model.DocumentStatus.DocumentStatus;
import UI.Components.AbstractEnhancedTableModel;

import java.util.ArrayList;

public class DocumentStatusEnhancedTableModel  extends AbstractEnhancedTableModel<DocumentStatus>
{
    public DocumentStatusEnhancedTableModel(ArrayList<DocumentStatus> data)
    {
        super("Document Status", new String[]{"ID", "Label"}, data);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        DocumentStatus documentStatus = getData().get(rowIndex);

        if (documentStatus == null)
        {
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> documentStatus.getId();
            case 1 -> documentStatus.getLabel();
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
