package Model.ProcessType;

import Exceptions.ProcessType.ProcessTypeException;

import java.util.HashMap;

public class MakeProcessType
{
    private static final HashMap<Integer, ProcessType> makeType = new HashMap<>();

    public static ProcessType getProcessType(Integer id, String label)
    {
        int typeHash = ProcessType.hashCode(id, label);

        if (makeType.containsKey(typeHash))
        {
            return makeType.get(typeHash);
        }
        else
        {
            ProcessType type;
            try
            {
                type = new ProcessType(id, label);
                makeType.put(typeHash, type);
            }
            catch (ProcessTypeException e)
            {
                type = null;
                System.err.println("Error creating ProcessType: " + e.getMessage());
            }
            return type;
        }
    }
}
