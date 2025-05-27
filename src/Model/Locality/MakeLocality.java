package Model.Locality;

import Exceptions.Locality.LocalityException;
import Model.Country.Country;

import java.util.HashMap;

public class MakeLocality
{
    private static final HashMap<Integer, Locality> localityMap = new HashMap<>();

    public static Locality getLocality(Integer id, String address, String postalCode, String number, Country country)
    {
        int localityHash = Locality.hashCode(id, address, postalCode, number, country);

        if(localityMap.containsKey(localityHash))
        {
            return localityMap.get(localityHash);
        }
        else
        {
            try
            {
                Locality locality = new Locality(id, address, postalCode, number, country);
                localityMap.put(localityHash, locality);
                return locality;
            }
            catch (LocalityException e)
            {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
