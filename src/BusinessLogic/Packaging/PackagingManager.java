package BusinessLogic.Packaging;

import DataAccess.Packaging.PackagingDBAccess;
import DataAccess.Packaging.PackagingDataAccess;
import Model.Packaging.Packaging;

import java.util.ArrayList;

public class PackagingManager
{
    private final PackagingDataAccess packagingDataAccess = new PackagingDBAccess();

    public PackagingManager()
    {

    }

    public void createPackaging(Packaging packaging)
    {
        packagingDataAccess.createPackaging(packaging);
    }

    public void updatePackaging(Packaging packaging)
    {
        packagingDataAccess.updatePackaging(packaging);
    }

    public void deletePackaging(int id)
    {
        packagingDataAccess.deletePackaging(id);
    }

    public Packaging getPackaging(int id)
    {
        return packagingDataAccess.getPackaging(id);
    }

    public ArrayList<Packaging> getAllPackagings()
    {
        return packagingDataAccess.getAllPackagings();
    }
}
