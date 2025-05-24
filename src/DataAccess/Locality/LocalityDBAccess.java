package DataAccess.Locality;

import DataAccess.Country.CountryDBAccess;
import DataAccess.DatabaseConnexion;
import Exceptions.Customer.CountryException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Locality.LocalityException;
import Model.Locality.Locality;
import Model.Locality.MakeLocality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocalityDBAccess implements LocalityDataAccess
{
    @Override
    public ArrayList<Locality> getAllLocalities() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Locality getLocality(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void createLocality(Locality locality) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateLocality(Locality locality) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteLocality(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ArrayList<Locality> getCustomerLocalities(int customerId)
    {
        String query = "SELECT * " +
                "FROM customer_locality " +
                "JOIN locality " +
                "JOIN country " +
                "WHERE customer_locality.id_customer = ? " +
                "AND customer_locality.id_locality = locality.id " +
                "AND locality.id_country = country.id";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setInt(1, customerId);

            ResultSet resultSet = statement.executeQuery();
            ArrayList<Locality> localities = new ArrayList<>();

            while (resultSet.next())
            {
                localities.add(makeLocalities(resultSet));

            }

            return localities;
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static Locality makeLocalities(ResultSet resultSet) throws SQLException, CountryException
    {
        return MakeLocality.getLocality(
                resultSet.getInt("id"),
                resultSet.getString("address"),
                resultSet.getString("postal_code"),
                resultSet.getString("number"),
                CountryDBAccess.makeCountry(resultSet)
        );
    }
}
