package DataAccess.Country;

import Model.Country.Country;

import java.util.ArrayList;

public interface CountryDataAccess
{
    void createCountry(Country country);
    void updateCountry(Country country);
    void deleteCountry(Integer id);
    Country getCountry(Integer id);
    ArrayList<Country> getAllCountries();
}
