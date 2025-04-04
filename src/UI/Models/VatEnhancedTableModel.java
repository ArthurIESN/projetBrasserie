package UI.Models;

import Model.Vat.Vat;
import UI.Components.AbstractEnhancedTableModel;

public class VatEnhancedTableModel extends AbstractEnhancedTableModel<Vat>
{
    public VatEnhancedTableModel(java.util.ArrayList<Vat> data)
    {
        super("VAT", new String[]{"Code", "Rate",}, data);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Vat vat = getData().get(rowIndex);

        if (vat == null)
        {
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> vat.getCode();
            case 1 -> vat.getRate();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return switch (columnIndex)
        {
            case 1 -> Double.class;
            default -> String.class;
        };
    }
}
