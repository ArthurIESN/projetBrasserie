package Controller.Event;

import BusinessLogic.Event.EventManager;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Event.GetDatesEventsException;
import Exceptions.Event.GetEventsBeforeDateException;
import Exceptions.Event.GetEventsWithItemException;
import Model.Event.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventController
{
    private static final EventManager eventManager = new EventManager();

    public static ArrayList<Event> getEventsWithSpecificItem(int idItem) throws GetEventsWithItemException
    {
        return eventManager.getEventsWithSpecificItem(idItem);
    }

    public static ArrayList<Event> getEventsBeforeDate(Date date) throws GetEventsBeforeDateException
    {
        return eventManager.getEventsBeforeDate(date);
    }

    public static ArrayList<Integer> getDatesEvents(Integer idEvent) throws GetDatesEventsException
    {
        return eventManager.getDatesEvents(idEvent);
    }

    public static void createEvent(Event event)
    {
        eventManager.createEvent(event);
    }

    public static void updateEvent(Event event)
    {
        eventManager.updateEvent(event);
    }

    public static void deleteEvent(int id)
    {
        eventManager.deleteEvent(id);
    }

    public static Event getEvent(int id)
    {
        return eventManager.getEvent(id);
    }

    public static ArrayList<Event> getAllEvents()
    {
        return eventManager.getAllEvents();
    }

    public static float getRealEventImpact(float impact)
    {
        return eventManager.getRealEventImpact(impact);
    }
}
