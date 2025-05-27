package Model.Supplier;

import Exceptions.Supplier.SupplierException;

import java.util.HashMap;

public class MakeSupplier
{
    private static final HashMap<Integer, Supplier> supplierMap = new HashMap<>();

    public static Supplier getSupplier(Integer id, String name)
    {
        int supplierHash = Supplier.hashCode(id, name);

        if(supplierMap.containsKey(supplierHash))
        {
            return supplierMap.get(supplierHash);
        }
        else
        {
            Supplier supplier;
            try {
                supplier = new Supplier(id, name);
                supplierMap.put(supplierHash, supplier);
            }catch (SupplierException e){
                supplier = null;
                System.err.println("Error creating Supplier: " + e.getMessage());
            }
            return supplier;
        }
    }
}
