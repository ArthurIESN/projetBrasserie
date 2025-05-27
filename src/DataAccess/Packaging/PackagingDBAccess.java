package DataAccess.Packaging;

import Model.Packaging.MakePackaging;
import Model.Packaging.Packaging;

import DataAccess.DataAccessUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PackagingDBAccess implements PackagingDataAccess
{
    @Override
    public void createPackaging(Packaging packaging) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updatePackaging(Packaging packaging) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deletePackaging(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Packaging getPackaging(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ArrayList<Packaging> getAllPackages() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static Packaging makePackaging(ResultSet resultSet) throws SQLException
    {
        if(!DataAccessUtils.hasColumn(resultSet, "packaging.id")) return null;

        return MakePackaging.getPackaging(
                resultSet.getInt("packaging.id"),
                resultSet.getString("packaging.label"),
                resultSet.getInt("packaging.quantity")
        );
    }
}
