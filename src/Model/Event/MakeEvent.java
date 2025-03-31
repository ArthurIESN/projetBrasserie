package Model.Event;

import java.util.Date;
import java.util.HashMap;

public class MakeEvent {
    private static final HashMap<Integer,Event> eventMap = new HashMap<>();

    public static Event getEvent(Integer id, String label, Date startDate, Date endDate, float impact){
        if(eventMap.containsKey(id)){
            return eventMap.get(id);
        }else {
            Event event = new Event(id,label,startDate,endDate,impact);
            eventMap.put(id,event);
            return event;
        }
    }
}
