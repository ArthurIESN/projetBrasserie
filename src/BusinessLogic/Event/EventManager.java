package BusinessLogic.Event;

import DataAccess.Event.EventDBAccess;
import DataAccess.Event.EventDataAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Event.GetEventsWithItemException;
import Model.Event.Event;

import java.util.ArrayList;

public class EventManager {
    private final EventDataAccess eventDBAccess = new EventDBAccess();

    public EventManager(){}

    public ArrayList<Event> getEventsWithSpecificItem(int idItem) throws GetEventsWithItemException {
        return eventDBAccess.getEventsWithSpecificItem(idItem);
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
}
