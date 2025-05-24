package Model.Event;

import java.sql.Date;
import java.util.HashMap;

public class MakeEvent {
    private static final HashMap<Integer,Event> eventMap = new HashMap<>();

    public static Event getEvent(Integer id, String label, Date startDate, Date endDate, float impact)
    {

        int eventHash = Event.hashCode(id, label, new java.util.Date(startDate.getTime()), new java.util.Date(endDate.getTime()), impact);

        if(eventMap.containsKey(eventHash))
        {
            return eventMap.get(eventHash);
        }
        else
        {
            try
            {
                Event event = new Event(id, label, startDate, endDate, impact);
                eventMap.put(eventHash,event);
                return event;
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
