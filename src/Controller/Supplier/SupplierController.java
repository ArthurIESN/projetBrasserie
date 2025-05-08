package Controller.Supplier;

import BusinessLogic.Supplier.SupplierManager;
import Exceptions.Supplier.GetAllSuppliersException;
import Model.Supplier.Supplier;

import java.util.ArrayList;

public class SupplierController {
    private static final SupplierManager supplierManager = new SupplierManager();

    public static ArrayList<Supplier> getAllSuppliers() throws GetAllSuppliersException
    {
        return supplierManager.getAllSuppliers();
    }

    public static Supplier getSupplier(int id)
    {
        return supplierManager.getSupplier(id);
    }

    public static void createSupplier(Supplier supplier)
    {
        supplierManager.createSupplier(supplier);
    }

    public static void deleteSupplier(int id)
    {
        supplierManager.deleteSupplier(id);
    }

    public static void updateSupplier(Supplier supplier)
    {
        supplierManager.updateSupplier(supplier);
    }


}
