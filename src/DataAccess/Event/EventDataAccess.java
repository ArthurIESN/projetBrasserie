package DataAccess.Event;

import Exceptions.Event.GetDatesEventsException;
import Exceptions.Event.GetEventsBeforeDateException;
import Exceptions.Event.GetEventsWithItemException;
import Model.Event.Event;

import java.util.ArrayList;
import java.util.Date;

public interface EventDataAccess {

    ArrayList<Integer> getDatesEvents(Integer idEvent)  throws GetDatesEventsException;
    ArrayList<Event> getEventsWithSpecificItem(int idItem) throws GetEventsWithItemException;

    ArrayList<Event> getEventsBeforeDate(Date date) throws GetEventsBeforeDateException;

    void createEvent(Event event);
    void updateEvent(Event event);
    void deleteEvent(int id);
    Event getEvent(int id);
    ArrayList<Event> getAllEvents();

}
