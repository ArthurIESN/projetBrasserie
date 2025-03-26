package DataAccess.ProcessStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAccess.DatabaseConnexion;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.ProcessStatus.GetAllProcessStatusException;

import Model.ProcessStatus;



public class ProcessStatusDBAccess
{
    public ArrayList<ProcessStatus> getAllProcessStatus() throws DatabaseConnectionFailedException, GetAllProcessStatusException
    {
        String query = "SELECT * FROM process_status";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<ProcessStatus> processStatuses = new ArrayList<>();

            while (resultSet.next())
            {
                processStatuses.add(createProcessStatusClass(resultSet));
            }

            return processStatuses;
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllProcessStatusException();
        }
    }

    private ProcessStatus createProcessStatusClass(ResultSet resultSet) throws SQLException
    {
        return new ProcessStatus(
                resultSet.getInt("id"),
                resultSet.getString("label")
        );
    }
}
