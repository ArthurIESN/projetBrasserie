package DataAccess.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAccess.DatabaseConnexion;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Supplier.GetAllSuppliersException;

import Model.Supplier;



public class SupplierDBAccess
{
    public ArrayList<Supplier> getAllSuppliers() throws DatabaseConnectionFailedException, GetAllSuppliersException
    {
        String query = "SELECT * FROM supplier";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Supplier> suppliers = new ArrayList<>();

            while (resultSet.next())
            {
                suppliers.add(createSupplierClass(resultSet));
            }

            return suppliers;

        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllSuppliersException();
        }
    }

    private Supplier createSupplierClass(ResultSet resultSet) throws SQLException
    {
        return new Supplier(
                resultSet.getInt("id"),
                resultSet.getString("name")
        );
    }
}




