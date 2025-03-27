package DataAccess.Type;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Type.GetAllTypesException;
import Model.Type.MakeType;
import Model.Type.Type;
import DataAccess.DatabaseConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeDBAccess implements TypeDataAccess
{

    public ArrayList<Type> getAllTypes() throws DatabaseConnectionFailedException, GetAllTypesException
    {
        String query = "SELECT * FROM type";

        try
        {
            Connection connection = DatabaseConnexion.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            ArrayList<Type> types = new ArrayList<>();

            while (resultSet.next())
            {
                Type type = MakeType.getType(resultSet.getInt("id"), resultSet.getString("label"));
                types.add(type);
            }

            return types;
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllTypesException();
        }
    }
}
