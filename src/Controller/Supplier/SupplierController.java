package Controller.Supplier;

import BusinessLogic.Supplier.SupplierManager;
import Exceptions.Supplier.GetAllSuppliersException;
import Model.Supplier.Supplier;

import java.util.ArrayList;

public class SupplierController {
    private static final SupplierManager supplierManager = new SupplierManager();

    // supplier
    public static ArrayList<Supplier> getAllSuppliers() throws GetAllSuppliersException
    {
        return supplierManager.getAllSuppliers();
    }
}
