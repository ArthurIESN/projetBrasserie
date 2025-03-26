package DataAccess.Supplier;

import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Supplier.GetAllSuppliersException;

import Model.Supplier;

public interface SupplierDataAccess
{
    ArrayList<Supplier> getAllSuppliers() throws DatabaseConnectionFailedException, GetAllSuppliersException;
    Supplier getSupplier(int id);
    void createSupplier(Supplier supplier);
    void deleteSupplier(int id);
    void updateSupplier(Supplier supplier);
}
