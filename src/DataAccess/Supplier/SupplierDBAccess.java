package DataAccess.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAccess.DatabaseConnexion;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Supplier.GetAllSuppliersException;

import Model.Supplier.MakeSupplier;
import Model.Supplier.Supplier;



public class SupplierDBAccess implements SupplierDataAccess
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
                Supplier supplier = MakeSupplier.getSupplier(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );

                suppliers.add(supplier);
            }

            return suppliers;

        } catch (SQLException e)
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
}




