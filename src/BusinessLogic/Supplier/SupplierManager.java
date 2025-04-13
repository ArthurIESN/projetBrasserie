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
}
