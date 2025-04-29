package DataAccess.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAccess.DataAccesUtils;
import DataAccess.DatabaseConnexion;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Supplier.GetAllSuppliersException;

import Model.Supplier.MakeSupplier;
import Model.Supplier.Supplier;



public class SupplierDBAccess implements SupplierDataAccess
{
    public ArrayList<Supplier> getAllSuppliers() throws GetAllSuppliersException
    {
        String query = "SELECT * FROM supplier";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Supplier> suppliers = new ArrayList<>();

            while (resultSet.next())
            {
                suppliers.add(makeSupplier(resultSet));
            }

            return suppliers;

        } catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllSuppliersException();
        }
    }

    public Supplier getSupplier(int id) {
        return null;
    }


    public void createSupplier(Supplier supplier) {

    }

    public void deleteSupplier(int id) {

    }

    public void updateSupplier(Supplier supplier) {

    }

    public static Supplier makeSupplier(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "supplier.id")) return null;

        return MakeSupplier.getSupplier(
                resultSet.getInt("supplier.id"),
                resultSet.getString("supplier.name")
        );
    }
}




