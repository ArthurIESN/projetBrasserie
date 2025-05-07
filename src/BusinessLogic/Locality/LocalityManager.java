package BusinessLogic.Locality;

import DataAccess.Locality.LocalityDBAccess;
import DataAccess.Locality.LocalityDataAccess;

import Model.Locality.Locality;

import java.util.ArrayList;

public class LocalityManager
{
    private final LocalityDataAccess localityDataAccess = new LocalityDBAccess();

    public ArrayList<Locality> getAllLocalities()
    {
        return localityDataAccess.getAllLocalities();
    }

    public Locality getLocality(int id)
    {
        return localityDataAccess.getLocality(id);
    }

    public void createLocality(Locality locality)
    {
        localityDataAccess.createLocality(locality);
    }

    public void updateLocality(Locality locality)
    {
        localityDataAccess.updateLocality(locality);
    }

    public void deleteLocality(int id)
    {
        localityDataAccess.deleteLocality(id);
    }

    public ArrayList<Locality> getCustomerLocalities(int customerId)
    {
        return localityDataAccess.getCustomerLocalities(customerId);
    }
}
