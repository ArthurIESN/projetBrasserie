package DataAccess.ProcessStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAccess.DatabaseConnexion;
import DataAccess.DataAccesUtils;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.ProcessStatus.GetAllProcessStatusException;

import Exceptions.ProcessStatus.GetProcessStatusException;
import Model.ProcessStatus.MakeProcessStatus;
import Model.ProcessStatus.ProcessStatus;



public class ProcessStatusDBAccess implements ProcessStatusDataAccess
{
    public ArrayList<ProcessStatus> getAllProcessStatus() throws GetAllProcessStatusException
    {
        String query = "SELECT * FROM process_status";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<ProcessStatus> processStatuses = new ArrayList<>();

            while (resultSet.next())
            {
                processStatuses.add(makeProcessStatus(resultSet));
            }

            return processStatuses;
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllProcessStatusException();
        }
    }

    @Override
    public void createProcessStatus(ProcessStatus processStatus) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateProcessStatus(ProcessStatus processStatus) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteProcessStatus(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ProcessStatus getProcessStatus(int id) throws GetProcessStatusException
    {
        String query = "SELECT * FROM process_status WHERE id = ?";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                return makeProcessStatus(resultSet);
            }
            else
            {
                throw new GetProcessStatusException("Process status not found");
            }
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetProcessStatusException("Failed to get process status");
        }
    }

    @Override
    public ArrayList<ProcessStatus> getProcessStatusWithSpecificType(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static ProcessStatus makeProcessStatus(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "process_status.id")) return null;

        return MakeProcessStatus.getProcessStatus(
                resultSet.getInt("process_status.id"),
                resultSet.getString("process_status.label")
        );
    }
}
