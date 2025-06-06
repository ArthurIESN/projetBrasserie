package DataAccess.Supplier;

import java.util.ArrayList;

import Exceptions.Supplier.GetAllSuppliersException;

import Model.Supplier.Supplier;

public interface SupplierDataAccess
{
    ArrayList<Supplier> getAllSuppliers() throws GetAllSuppliersException;
    Supplier getSupplier(int id);
    void createSupplier(Supplier supplier);
    void deleteSupplier(int id);
    void updateSupplier(Supplier supplier);
}
