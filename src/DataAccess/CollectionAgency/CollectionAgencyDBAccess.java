package DataAccess.CollectionAgency;

import DataAccess.DataAccesUtils;
import Exceptions.CollectionAgency.CollectionAgencyException;
import Model.CollectionAgency.CollectionAgency;
import Model.CollectionAgency.MakeCollectionAgency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CollectionAgencyDBAccess implements CollectionAgencyDataAccess
{
    @Override
    public void createCollectionAgency(CollectionAgency collectionAgency)
    {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateCollectionAgency(CollectionAgency collectionAgency) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteCollectionAgency(Integer id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public CollectionAgency getCollectionAgency(Integer id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ArrayList<CollectionAgency> getAllCollectionAgencies() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static CollectionAgency makeCollectionAgency(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "collection_agency.id")) return null;

        return MakeCollectionAgency.getCollectionAgency
                (
                        resultSet.getInt("collection_agency.id"),
                        resultSet.getString("collection_agency.name")
                );
    }
}
