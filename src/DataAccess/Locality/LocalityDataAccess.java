package DataAccess.Locality;

import Model.Locality.Locality;

import java.util.ArrayList;

public interface LocalityDataAccess
{
    ArrayList<Locality> getAllLocalities();
    Locality getLocality(int id);
    void createLocality(Locality locality);
    void updateLocality(Locality locality);
    void deleteLocality(int id);

    ArrayList<Locality> getCustomerLocalities(int customerId);
}
