package Model.Country;

import Exceptions.Customer.CountryException;

import java.util.HashMap;

public class MakeCountry
{
    private static final HashMap<Integer, Country> countryMap = new HashMap<>();

    public static Country getCountry(Integer id, String name, float deliveryCost)
    {
        int countryHash = Country.hashCode(id, name, deliveryCost);

        if(countryMap.containsKey(countryHash))
        {
            return countryMap.get(countryHash);
        }
        else
        {
            try
            {
                Country country = new Country(id, name, deliveryCost);
                countryMap.put(countryHash, country);
                return country;
            }
            catch (CountryException e)
            {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
