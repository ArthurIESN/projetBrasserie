package UI.Models;

import Model.Event.Event;
import UI.Components.AbstractEnhancedTableModel;

import java.util.ArrayList;

public class EventEnhancedTableModel extends AbstractEnhancedTableModel<Event>
{
    public EventEnhancedTableModel(ArrayList<Event> data)
    {
        super("Event", new String[]{"ID", "Label", "Start Date", "End Date", "Impact"}, data);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Event event = getData().get(rowIndex);

        if (event == null)
        {
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> event.getId();
            case 1 -> event.getLabel();
            case 2 -> event.getStartDate();
            case 3 -> event.getEndDate();
            case 4 -> event.getImpact();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return switch (columnIndex)
        {
            case 0 -> Integer.class;
            case 2, 3 -> java.util.Date.class;
            default -> String.class;
        };
    }
}
