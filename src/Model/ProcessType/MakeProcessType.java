package Model.ProcessType;

import java.util.HashMap;

public class MakeProcessType
{
    private static final HashMap<Integer, ProcessType> makeType = new HashMap<>();

    public static ProcessType getProcessType(Integer id, String label)
    {
        if (makeType.containsKey(id))
        {
            return makeType.get(id);
        }
        else
        {
            ProcessType type = new ProcessType(id, label);
            makeType.put(id, type);
            return type;
        }
    }
}
