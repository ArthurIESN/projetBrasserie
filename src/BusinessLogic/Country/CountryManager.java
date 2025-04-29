package BusinessLogic.Country;

import DataAccess.Country.CountryDBAccess;
import DataAccess.Country.CountryDataAccess;
import Model.Country.Country;


import java.util.ArrayList;

public class CountryManager
{
    private final CountryDataAccess countryDataAccess = new CountryDBAccess();

    public void createCountry(Country country)
    {
        countryDataAccess.createCountry(country);
    }

    public void updateCountry(Country country)
    {
        countryDataAccess.updateCountry(country);
    }

    public void deleteCountry(Integer id)
    {
        countryDataAccess.deleteCountry(id);
    }

    public Country getCountry(Integer id)
    {
        return countryDataAccess.getCountry(id);
    }

    public ArrayList<Country> getAllCountries()
    {
        return countryDataAccess.getAllCountries();
    }

}
