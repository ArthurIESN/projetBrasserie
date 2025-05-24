package UI.Models;

import Model.CollectionAgency.CollectionAgency;
import UI.Components.EnhancedTable.AbstractEnhancedTableModel;

import java.util.ArrayList;

public class CollectionAgencyEnhancedTableModel extends AbstractEnhancedTableModel<CollectionAgency> {
    public CollectionAgencyEnhancedTableModel(ArrayList<CollectionAgency> data){
        super("Collection Agency", new String[]{"ID", "Name"}, data);
    }

    public CollectionAgencyEnhancedTableModel(){
        this(new ArrayList<CollectionAgency>());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CollectionAgency collectionAgency = (CollectionAgency) getData().get(rowIndex);

        if (collectionAgency == null){
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> collectionAgency.getId();
            case 1 -> collectionAgency.getName();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnIndex == 0 ? Integer.class : String.class;
    }
}
