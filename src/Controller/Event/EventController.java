package Controller.Event;

import BusinessLogic.Event.EventManager;
import Exceptions.Event.GetEventsWithItemException;
import Model.Event.Event;

import java.util.ArrayList;

public class EventController
{
    private static final EventManager eventManager = new EventManager();

    public ArrayList<Event> getEventsWithSpecificItem(int idItem) throws GetEventsWithItemException
    {
        return eventManager.getEventsWithSpecificItem(idItem);
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
}
