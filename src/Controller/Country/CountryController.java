package Controller.Country;

import BusinessLogic.Country.CountryManager;
import Model.Country.Country;

import java.util.ArrayList;

public class CountryController
{
    private static final CountryManager countryManager = new CountryManager();

    public static void createCountry(Country country)
    {
        countryManager.createCountry(country);
    }

    public static void updateCountry(Country country)
    {
        countryManager.updateCountry(country);
    }

    public static void deleteCountry(Integer id)
    {
        countryManager.deleteCountry(id);
    }

    public static Country getCountry(Integer id)
    {
        return countryManager.getCountry(id);
    }

    public static ArrayList<Country> getAllCountries()
    {
        return countryManager.getAllCountries();
    }
}
