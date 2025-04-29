package DataAccess.CollectionAgency;

import DataAccess.DataAccesUtils;
import Model.CollectionAgency.CollectionAgency;
import Model.CollectionAgency.MakeCollectionAgency;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionAgencyDBAccess implements CollectionAgencyDataAccess
{


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
