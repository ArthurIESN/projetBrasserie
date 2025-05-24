package Controller.Packaging;

import BusinessLogic.Packaging.PackagingManager;
import Model.Packaging.Packaging;

import java.util.ArrayList;

public class PackagingController
{
    private static final PackagingManager packagingManager = new PackagingManager();

    public static void createPackaging(Packaging packaging)
    {
        packagingManager.createPackaging(packaging);
    }

    public static void updatePackaging(Packaging packaging)
    {
        packagingManager.updatePackaging(packaging);
    }

    public static void deletePackaging(int id)
    {
        packagingManager.deletePackaging(id);
    }

    public static Packaging getPackaging(int id)
    {
        return packagingManager.getPackaging(id);
    }

    public static ArrayList<Packaging> getAllPackages()
    {
        return packagingManager.getAllPackages();
    }
}
