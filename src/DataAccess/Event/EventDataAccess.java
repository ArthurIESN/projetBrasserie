package DataAccess.Event;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Event.GetEventsWithItemException;
import Model.Event.Event;

import java.util.ArrayList;

public interface EventDataAccess {
    ArrayList<Event> getEventsWithSpecificItem(int idItem) throws DatabaseConnectionFailedException, GetEventsWithItemException;

}
