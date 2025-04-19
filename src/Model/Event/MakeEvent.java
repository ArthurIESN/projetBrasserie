package Model.Event;

import Model.Employee.Employee;

import java.util.Date;
import java.util.HashMap;

public class MakeEvent {
    private static final HashMap<Integer,Event> eventMap = new HashMap<>();

    public static Event getEvent(Integer id, String label, Date startDate, Date endDate, float impact)
    {
        Event event = new Event(id, label, startDate, endDate, impact);
        int eventHash = event.hashCode();

        if(eventMap.containsKey(eventHash))
        {
            return eventMap.get(eventHash);
        }
        else
        {
            eventMap.put(eventHash,event);
            return event;
        }
    }
}
