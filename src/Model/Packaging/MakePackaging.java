package Model.Packaging;

import java.util.HashMap;

public class MakePackaging
{
    private static final HashMap<Integer, Packaging> packagingMap = new HashMap<>();

    public static Packaging getPackaging(Integer id, String label, Integer quantity)
    {
        if(packagingMap.containsKey(id))
        {
            return packagingMap.get(id);
        }
        else
        {
            Packaging packaging = new Packaging(id, label, quantity);
            packagingMap.put(id, packaging);
            return packaging;
        }
    }
}
