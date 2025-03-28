package Model.ProcessStatus;

import java.util.HashMap;

public class MakeProcessStatus
{
    private static final HashMap<Integer, ProcessStatus> processStatusMap = new HashMap<>();

    public static ProcessStatus getProcessStatus(Integer id, String label)
    {
        if(processStatusMap.containsKey(id))
        {
            return processStatusMap.get(id);
        }
        else
        {
            ProcessStatus processStatus = new ProcessStatus(id, label);
            processStatusMap.put(id, processStatus);
            return processStatus;
        }
    }
}
