package BusinessLogic.CollectionAgency;

import DataAccess.CollectionAgency.CollectionAgencyDataAccess;
import DataAccess.CollectionAgency.CollectionAgencyDBAccess;
import Model.CollectionAgency.CollectionAgency;

import java.util.ArrayList;

public class CollectionAgencyManager
{
    private final CollectionAgencyDataAccess collectionAgencyDataAccess = new CollectionAgencyDBAccess();

    public void createCollectionAgency(CollectionAgency collectionAgency)
    {
        collectionAgencyDataAccess.createCollectionAgency(collectionAgency);
    }
    public void updateCollectionAgency(CollectionAgency collectionAgency)
    {
        collectionAgencyDataAccess.updateCollectionAgency(collectionAgency);
    }
    public void deleteCollectionAgency(Integer id)
    {
        collectionAgencyDataAccess.deleteCollectionAgency(id);
    }
    public CollectionAgency getCollectionAgency(Integer id)
    {
        return collectionAgencyDataAccess.getCollectionAgency(id);
    }
    public ArrayList<CollectionAgency> getAllCollectionAgencies()
    {
        return collectionAgencyDataAccess.getAllCollectionAgencies();
    }

}
