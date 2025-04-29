package DataAccess.CollectionAgency;

import Model.CollectionAgency.CollectionAgency;

import java.util.ArrayList;

public interface CollectionAgencyDataAccess
{
    void createCollectionAgency(CollectionAgency collectionAgency);
    void updateCollectionAgency(CollectionAgency collectionAgency);
    void deleteCollectionAgency(Integer id);
    CollectionAgency getCollectionAgency(Integer id);
    ArrayList<CollectionAgency> getAllCollectionAgencies();
}
