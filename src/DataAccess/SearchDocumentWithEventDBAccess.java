package DataAccess.Search;

import DataAccess.DatabaseConnexion;
import Exceptions.DataAccess.DatabaseConnectionFailedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchDocumentWithEventDBAccess {
    public List<Date> getDatesEvents() throws DatabaseConnectionFailedException {
        List<Date> dates = new ArrayList<>();

        String query = "SELECT start_date FROM event";

        try(Connection connection = DatabaseConnexion.getInstance();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()){
                dates.add(resultSet.getDate("start_date"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return dates;
    }
}