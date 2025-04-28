package DataAccess.Event;

import BusinessLogic.Event.EventManager;
import DataAccess.DatabaseConnexion;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Event.GetEventsWithItemException;
import Model.Event.Event;
import Model.Event.MakeEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDBAccess implements EventDataAccess {
    public EventDBAccess(){}

    public ArrayList<Event> getEventsWithSpecificItem(int idItem) throws GetEventsWithItemException {
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

            while (resultSet.next()){
                Event event = MakeEvent.getEvent(
                  resultSet.getInt("id"),
                  resultSet.getString("label"),
                  resultSet.getDate("start_date"),
                  resultSet.getDate("end_date"),
                  resultSet.getFloat("impact")
                );



                events.add(event);

            }

         return events;
        }catch (SQLException | DatabaseConnectionFailedException e){
            System.out.println(e.getMessage());
            throw new GetEventsWithItemException();
        }
    }
}
