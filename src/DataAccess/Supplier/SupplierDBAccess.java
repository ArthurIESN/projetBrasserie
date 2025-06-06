package DataAccess.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAccess.DataAccessUtils;
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
                Supplier supplier = makeSupplier(resultSet);

                if(supplier != null)
                {
                    suppliers.add(supplier);
                }
            }

            return suppliers;

        } catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllSuppliersException();
        }
    }

    public Supplier getSupplier(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    public void createSupplier(Supplier supplier) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void deleteSupplier(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void updateSupplier(Supplier supplier) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static Supplier makeSupplier(ResultSet resultSet) throws SQLException
    {
        if(!DataAccessUtils.hasColumn(resultSet, "supplier.id")) return null;

        return MakeSupplier.getSupplier(
                resultSet.getInt("supplier.id"),
                resultSet.getString("supplier.name")
        );
    }
}




