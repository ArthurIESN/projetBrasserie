package Model.Event;

import Model.Employee.Employee;

import java.util.Date;
import java.util.HashMap;

public class MakeEvent {
    private static final HashMap<Integer,Event> eventMap = new HashMap<>();

    public static Event getEvent(Integer id, String label, Date startDate, Date endDate, float impact)
    {

        int eventHash = Event.hashCode(id, label, startDate, endDate, impact);

        if(eventMap.containsKey(eventHash))
        {
            return eventMap.get(eventHash);
        }
        else
        {
            Event event = new Event(id, label, startDate, endDate, impact);
            eventMap.put(eventHash,event);
            return event;
        }
    }
}
