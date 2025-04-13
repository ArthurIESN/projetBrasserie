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
        String query = "SELECT DISTINCT e.* " +
                "FROM Event e " +
                "JOIN event_document_details edd ON e.id = edd.id_event " +
                "JOIN Document_details dd ON edd.id_document_details = dd.id " +
                "JOIN item_document_details idd ON dd.id = idd.id_document_details " +
                "WHERE idd.id_item = ?";

        try{
            Connection dataBaseConnection = DatabaseConnexion.getInstance().getConnection();
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
