package Model.ProcessType;

import java.util.HashMap;

public class MakeProcessType
{
    private static final HashMap<Integer, ProcessType> makeType = new HashMap<>();

    public static ProcessType getProcessType(Integer id, String label)
    {
        ProcessType type = new ProcessType(id, label);
        int typeHash = type.hashCode();

        if (makeType.containsKey(typeHash))
        {
            return makeType.get(typeHash);
        }
        else
        {
            makeType.put(typeHash, type);
            return type;
        }
    }
}
