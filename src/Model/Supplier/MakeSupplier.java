package Model.Supplier;

import java.util.HashMap;

public class MakeSupplier
{
    private static final HashMap<Integer, Supplier> supplierMap = new HashMap<>();

    public static Supplier getSupplier(Integer id, String name)
    {
        if(supplierMap.containsKey(id))
        {
            return supplierMap.get(id);
        }
        else
        {
            Supplier supplier = new Supplier(id, name);
            supplierMap.put(id, supplier);
            return supplier;
        }
    }
}
