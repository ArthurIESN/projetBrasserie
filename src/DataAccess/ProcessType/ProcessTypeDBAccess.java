package DataAccess.ProcessType;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.ProcessType.GetAllProcessTypesException;
import Exceptions.ProcessType.GetProcessTypeException;
import Model.ProcessType.MakeProcessType;
import Model.ProcessType.ProcessType;

import DataAccess.DatabaseConnexion;
import DataAccess.DataAccesUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProcessTypeDBAccess implements ProcessTypeDataAccess
{

    public ArrayList<ProcessType> getAllTypes() throws GetAllProcessTypesException
    {
        String query = "SELECT * FROM process_type";

        try
        {
            Connection connection = DatabaseConnexion.getInstance();

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            ArrayList<ProcessType> types = new ArrayList<>();

            while (resultSet.next())
            {
                types.add(makeProcessType(resultSet));
            }

            return types;
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllProcessTypesException();
        }
    }

    @Override
    public void createProcessType(ProcessType processType) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateProcessType(ProcessType processType) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteProcessType(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ProcessType getProcessType(int id) throws GetProcessTypeException
    {
        String query = "SELECT * FROM process_type WHERE id = ?";

        try
        {
            Connection connection = DatabaseConnexion.getInstance();

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                return makeProcessType(resultSet);
            }
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetProcessTypeException("Error while getting process type" );
        }

        return null;
    }

    public static ProcessType makeProcessType(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "process_type.id")) return null;

        return MakeProcessType.getProcessType(
                resultSet.getInt("process_type.id"),
                resultSet.getString("process_type.label")
        );
    }
}
