package Model.CollectionAgency;

import java.util.HashMap;

public class MakeCollectionAgency
{
    private static final HashMap<Integer, CollectionAgency> collectionAgencyMap = new HashMap<>();

    public static CollectionAgency getCollectionAgency(Integer id, String name)
    {
        int collectionAgencyHash = CollectionAgency.hashCode(id, name);

        if(collectionAgencyMap.containsKey(collectionAgencyHash))
        {
            return collectionAgencyMap.get(collectionAgencyHash);
        }
        else
        {
            CollectionAgency collectionAgency = new CollectionAgency(id, name);
            collectionAgencyMap.put(collectionAgencyHash,collectionAgency);
            return collectionAgency;
        }
    }
}
