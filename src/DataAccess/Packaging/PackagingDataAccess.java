package DataAccess.Packaging;

import Model.Packaging.Packaging;

import java.util.ArrayList;

public interface PackagingDataAccess
{
    void createPackaging(Packaging packaging);
    void updatePackaging(Packaging packaging);
    void deletePackaging(int id);
    Packaging getPackaging(int id);
    ArrayList<Packaging> getAllPackages();
}
