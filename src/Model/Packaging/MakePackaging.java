package Model.Packaging;

import java.util.HashMap;

public class MakePackaging
{
    private static final HashMap<Integer, Packaging> packagingMap = new HashMap<>();

    public static Packaging getPackaging(Integer id, String label, Integer quantity)
    {
        int packagingHash = Packaging.hashCode(id, label, quantity);

        if(packagingMap.containsKey(packagingHash))
        {
            return packagingMap.get(packagingHash);
        }
        else
        {
            Packaging packaging = new Packaging(id, label, quantity);
            packagingMap.put(packagingHash, packaging);
            return packaging;
        }
    }
}
