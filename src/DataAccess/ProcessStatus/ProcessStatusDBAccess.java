package DataAccess.ProcessStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAccess.DatabaseConnexion;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.ProcessStatus.GetAllProcessStatusException;

import Model.ProcessStatus.MakeProcessStatus;
import Model.ProcessStatus.ProcessStatus;



public class ProcessStatusDBAccess implements ProcessStatusDataAccess
{
    public ArrayList<ProcessStatus> getAllProcessStatus() throws GetAllProcessStatusException
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
                ProcessStatus processStatus = MakeProcessStatus.getProcessStatus(
                        resultSet.getInt("id"),
                        resultSet.getString("label")
                );

                processStatuses.add(processStatus);
            }

            return processStatuses;
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllProcessStatusException();
        }
    }
}
