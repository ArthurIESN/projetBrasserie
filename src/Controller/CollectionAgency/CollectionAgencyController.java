package Controller.CollectionAgency;

import BusinessLogic.CollectionAgency.CollectionAgencyManager;
import Model.CollectionAgency.CollectionAgency;

import java.util.ArrayList;

public class CollectionAgencyController
{
    private static final CollectionAgencyManager collectionAgencyManager = new CollectionAgencyManager();

    public static void createCollectionAgency(CollectionAgency collectionAgency)
    {
        collectionAgencyManager.createCollectionAgency(collectionAgency);
    }
    public void updateCollectionAgency(CollectionAgency collectionAgency)
    {
        collectionAgencyManager.updateCollectionAgency(collectionAgency);
    }
    public void deleteCollectionAgency(Integer id)
    {
        collectionAgencyManager.deleteCollectionAgency(id);
    }
    public CollectionAgency getCollectionAgency(Integer id)
    {
        return collectionAgencyManager.getCollectionAgency(id);
    }
    public ArrayList<CollectionAgency> getAllCollectionAgencies()
    {
        return collectionAgencyManager.getAllCollectionAgencies();
    }

}
