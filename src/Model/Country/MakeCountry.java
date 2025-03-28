package Model.Country;

import java.util.HashMap;

public class MakeCountry
{
    private static final HashMap<Integer, Country> countryMap = new HashMap<>();

    public static Country getCountry(Integer id, String name, float deliveryCost)
    {
        if(countryMap.containsKey(id))
        {
            return countryMap.get(id);
        }
        else
        {
            Country country = new Country(id, name, deliveryCost);
            countryMap.put(id, country);
            return country;
        }
    }
}
