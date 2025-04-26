package Model.Country;

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
            Country country = new Country(id, name, deliveryCost);
            countryMap.put(countryHash, country);
            return country;
        }
    }
}
