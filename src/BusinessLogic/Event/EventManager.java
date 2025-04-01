package BusinessLogic.Event;

import DataAccess.Event.EventDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Event.GetEventsWithItemException;
import Model.Event.Event;

import java.util.ArrayList;

public class EventManager {
    private EventDBAccess eventDBAccess = new EventDBAccess();

    public EventManager(){}

    public ArrayList<Event> getEventsWithSpecificItem(int idItem) throws DatabaseConnectionFailedException, GetEventsWithItemException {
        return eventDBAccess.getEventsWithSpecificItem(idItem);
    }
}
