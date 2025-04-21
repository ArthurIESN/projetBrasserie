package Model.Packaging;

import java.util.HashMap;

public class MakePackaging
{
    private static final HashMap<Integer, Packaging> packagingMap = new HashMap<>();

    public static Packaging getPackaging(Integer id, String label, Integer quantity)
    {
        Packaging packaging = new Packaging(id, label, quantity);
        int packagingHash = packaging.hashCode();

        if(packagingMap.containsKey(packagingHash))
        {
            return packagingMap.get(packagingHash);
        }
        else
        {
            packagingMap.put(packagingHash, packaging);
            return packaging;
        }
    }
}
