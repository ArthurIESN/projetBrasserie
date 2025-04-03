package DataAccess.Search.SearchDocumentWithEvent;

import DataAccess.DatabaseConnexion;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.GetQuantityItemWithSpecificEventException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchDocumentWithEventDBAccess implements SearchDocumentWithEventDataAccess {
    public List<Integer> getDatesEvents(Integer idEvent)  throws DatabaseConnectionFailedException {

        String query = "SELECT DISTINCT YEAR(start_date) AS start_date FROM event WHERE id = ?";


        try
        {
            Connection connection = DatabaseConnexion.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,idEvent);
            ResultSet resultSet = statement.executeQuery();
            List<Integer> dates = new ArrayList<>();

            while (resultSet.next()) {
                dates.add(resultSet.getInt("start_date"));
            }
            return dates;
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
            throw new DatabaseConnectionFailedException();
        }

    }

    public ArrayList<Float> getQuantityItemWithSpecificEvent(int idEvent) throws DatabaseConnectionFailedException, GetQuantityItemWithSpecificEventException {
        String query = "SELECT  new_quantity  FROM document_details dd " +
                "JOIN event_document_details edd ON edd.id_document_details = dd.id " +
                "WHERE edd.id_event = ?";

        try{
            Connection dataBaseConnection = DatabaseConnexion.getInstance().getConnection();
            PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(query);
            preparedStatement.setInt(1,idEvent);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Float> quantities = new ArrayList<>();
            while(resultSet.next()){
                quantities.add(resultSet.getFloat("new_quantity"));
            }
            return quantities;
        }catch (SQLException  e){
            System.err.println(e.getMessage());
            throw new GetQuantityItemWithSpecificEventException();
        }
    }
}
