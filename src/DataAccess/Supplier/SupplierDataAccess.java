package DataAccess.Supplier;

import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Supplier.GetAllSuppliersException;

import Model.Supplier;

public interface SupplierDataAccess
{
    public ArrayList<Supplier> getAllSuppliers() throws DatabaseConnectionFailedException, GetAllSuppliersException;
    public Supplier getSupplier(int id);
    public void createSupplier(Supplier supplier);
    public void deleteSupplier(int id);
    public void updateSupplier(Supplier supplier);
}
