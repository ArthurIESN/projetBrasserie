package DataAccess.Event;

import BusinessLogic.Event.EventManager;
import DataAccess.DataAccesUtils;
import DataAccess.DatabaseConnexion;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Event.GetEventsBeforeDateException;
import Exceptions.Event.GetEventsWithItemException;
import Model.Event.Event;
import Model.Event.MakeEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDBAccess implements EventDataAccess
{
    public EventDBAccess(){}

    public ArrayList<Event> getEventsWithSpecificItem(int idItem) throws GetEventsWithItemException
    {
        String query = "SELECT DISTINCT event.* " +
                "FROM event " +
                "JOIN event_document_details ON event.id = event_document_details.id_event " +
                "JOIN document_details ON event_document_details.id_document_details = document_details.id " +
                "JOIN item ON document_details.id_item = item.id " +
                "WHERE item.id = ?";

        try{
            Connection dataBaseConnection = DatabaseConnexion.getInstance();
            PreparedStatement statement = dataBaseConnection.prepareStatement(query);
            statement.setInt(1,idItem);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Event> events = new ArrayList<>();

            while (resultSet.next())
            {
                events.add(makeEvent(resultSet));
            }

         return events;
        }catch (SQLException | DatabaseConnectionFailedException e){
            System.out.println(e.getMessage());
            throw new GetEventsWithItemException();
        }
    }

    @Override
    public ArrayList<Event> getEventsBeforeDate(Date date) throws GetEventsBeforeDateException
    {
        String query = "SELECT * " +
                "FROM event " +
                "WHERE start_date <= ? " +
                "AND start_date > ? " +
                "ORDER BY start_date";

        try{
            Connection dataBaseConnection = DatabaseConnexion.getInstance();
            PreparedStatement statement = dataBaseConnection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(date.getTime()));
            statement.setDate(2, new java.sql.Date(new Date().getTime()));
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Event> events = new ArrayList<>();

            while (resultSet.next())
            {
                events.add(makeEvent(resultSet));
            }

            return events;
        }catch (SQLException | DatabaseConnectionFailedException e){
            System.out.println(e.getMessage());
            throw new GetEventsBeforeDateException("Error while getting events before date");
        }
    }

    @Override
    public void createEvent(Event event) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateEvent(Event event) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteEvent(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Event getEvent(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ArrayList<Event> getAllEvents() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static Event makeEvent(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "event.id")) return null;

        return MakeEvent.getEvent(
                resultSet.getInt("event.id"),
                resultSet.getString("event.label"),
                resultSet.getDate("event.start_date"),
                resultSet.getDate("event.end_date"),
                resultSet.getFloat("event.impact")
        );
    }
}
