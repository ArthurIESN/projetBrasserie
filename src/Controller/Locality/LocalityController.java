package Controller.Locality;

import BusinessLogic.Locality.LocalityManager;
import Model.Locality.Locality;

import java.util.ArrayList;

public class LocalityController
{
    private static final LocalityManager localityManager = new LocalityManager();

    public static ArrayList<Locality> getAllLocalities()
    {
        return localityManager.getAllLocalities();
    }

    public static Locality getLocality(int id)
    {
        return localityManager.getLocality(id);
    }

    public static void createLocality(Locality locality)
    {
        localityManager.createLocality(locality);
    }

    public static void updateLocality(Locality locality)
    {
        localityManager.updateLocality(locality);
    }

    public static void deleteLocality(int id)
    {
        localityManager.deleteLocality(id);
    }

    public static ArrayList<Locality> getCustomerLocalities(int customerId)
    {
        return localityManager.getCustomerLocalities(customerId);
    }
}
