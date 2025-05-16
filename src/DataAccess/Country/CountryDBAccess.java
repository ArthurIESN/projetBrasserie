package DataAccess.Country;

import DataAccess.DataAccesUtils;
import Exceptions.Customer.CountryException;
import Model.Country.Country;
import Model.Country.MakeCountry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CountryDBAccess implements CountryDataAccess
{
    @Override
    public void createCountry(Country country) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateCountry(Country country) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteCountry(Integer id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Country getCountry(Integer id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ArrayList<Country> getAllCountries() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static Country makeCountry(ResultSet resultSet) throws SQLException, CountryException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "country.id")) return null;

        return MakeCountry.getCountry
                (
                        resultSet.getInt("country.id"),
                        resultSet.getString("country.name"),
                        resultSet.getFloat("country.delivery_cost")
                );
    }
}
