package Model.Country;

import java.util.HashMap;

public class MakeCountry
{
    private static final HashMap<Integer, Country> countryMap = new HashMap<>();

    public static Country getCountry(Integer id, String name, float deliveryCost)
    {
        Country country = new Country(id, name, deliveryCost);
        int countryHash = country.hashCode();

        if(countryMap.containsKey(countryHash))
        {
            return countryMap.get(countryHash);
        }
        else
        {
            countryMap.put(countryHash, country);
            return country;
        }
    }
}
