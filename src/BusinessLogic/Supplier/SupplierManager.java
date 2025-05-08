package BusinessLogic.Supplier;

import java.util.ArrayList;

import DataAccess.Supplier.SupplierDBAccess;
import DataAccess.Supplier.SupplierDataAccess;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Supplier.GetAllSuppliersException;

import Model.Supplier.Supplier;



public class SupplierManager
{
    private final SupplierDataAccess supplierDataAccess;

    public SupplierManager()
    {
        supplierDataAccess = new SupplierDBAccess();
    }

    public ArrayList<Supplier> getAllSuppliers() throws GetAllSuppliersException
    {
        return supplierDataAccess.getAllSuppliers();
    }

    public Supplier getSupplier(int id)
    {
        return supplierDataAccess.getSupplier(id);
    }

    public void createSupplier(Supplier supplier)
    {
        supplierDataAccess.createSupplier(supplier);
    }

    public void deleteSupplier(int id)
    {
        supplierDataAccess.deleteSupplier(id);
    }

    public void updateSupplier(Supplier supplier)
    {
        supplierDataAccess.updateSupplier(supplier);
    }
}
