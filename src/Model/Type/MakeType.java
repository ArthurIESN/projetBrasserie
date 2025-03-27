package Model.Type;

import java.util.HashMap;

public class MakeType
{
    private static final HashMap<Integer, Type> makeType = new HashMap<>();

    public static Type getType(Integer id, String label)
    {
        if (makeType.containsKey(id))
        {
            return makeType.get(id);
        }
        else
        {
            Type type = new Type(id, label);
            makeType.put(id, type);
            return type;
        }
    }
}
