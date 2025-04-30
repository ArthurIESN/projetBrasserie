package BusinessLogic.Event;

import DataAccess.Event.EventDBAccess;
import DataAccess.Event.EventDataAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Event.GetEventsBeforeDateException;
import Exceptions.Event.GetEventsWithItemException;
import Model.Event.Event;

import java.util.ArrayList;
import java.util.Date;

public class EventManager {
    private final EventDataAccess eventDBAccess = new EventDBAccess();

    public EventManager(){}

    public ArrayList<Event> getEventsWithSpecificItem(int idItem) throws GetEventsWithItemException {
        return eventDBAccess.getEventsWithSpecificItem(idItem);
    }

    public ArrayList<Event> getEventsBeforeDate(Date date) throws GetEventsBeforeDateException
    {
        return eventDBAccess.getEventsBeforeDate(date);
    }

    public void createEvent(Event event) {
        eventDBAccess.createEvent(event);
    }

    public void updateEvent(Event event) {
        eventDBAccess.updateEvent(event);
    }

    public void deleteEvent(int id) {
        eventDBAccess.deleteEvent(id);
    }

    public Event getEvent(int id) {
        return eventDBAccess.getEvent(id);
    }

    public ArrayList<Event> getAllEvents() {
        return eventDBAccess.getAllEvents();
    }

    public float getRealEventImpact(float impact)
    {
        return impact / 100.f;
    }
}
