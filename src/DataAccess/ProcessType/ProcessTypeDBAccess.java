package DataAccess.ProcessType;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.ProcessType.GetAllProcessTypesException;
import Model.ProcessType.MakeProcessType;
import Model.ProcessType.ProcessType;
import DataAccess.DatabaseConnexion;

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
                ProcessType type = MakeProcessType.getProcessType(resultSet.getInt("id"), resultSet.getString("label"));
                types.add(type);
            }

            return types;
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllProcessTypesException();
        }
    }
}
