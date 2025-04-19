package Model.Supplier;

import java.util.HashMap;

public class MakeSupplier
{
    private static final HashMap<Integer, Supplier> supplierMap = new HashMap<>();

    public static Supplier getSupplier(Integer id, String name)
    {
        Supplier supplier = new Supplier(id, name);
        int supplierHash = supplier.hashCode();

        if(supplierMap.containsKey(supplierHash))
        {
            return supplierMap.get(supplierHash);
        }
        else
        {
            supplierMap.put(supplierHash, supplier);
            return supplier;
        }
    }
}
