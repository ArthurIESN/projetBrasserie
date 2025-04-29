package DataAccess.Packaging;

import Model.Packaging.MakePackaging;
import Model.Packaging.Packaging;

import DataAccess.DataAccesUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PackagingDBAccess implements PackagingDataAccess
{


    public static Packaging makePackaging(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "packaging.id")) return null;

        return MakePackaging.getPackaging(
                resultSet.getInt("packaging.id"),
                resultSet.getString("packaging.label"),
                resultSet.getInt("packaging.quantity")
        );
    }
}
