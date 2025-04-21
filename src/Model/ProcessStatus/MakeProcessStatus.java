package Model.ProcessStatus;

import java.util.HashMap;

public class MakeProcessStatus
{
    private static final HashMap<Integer, ProcessStatus> processStatusMap = new HashMap<>();

    public static ProcessStatus getProcessStatus(Integer id, String label)
    {
        ProcessStatus processStatus = new ProcessStatus(id, label);
        int processStatusHash = processStatus.hashCode();

        if(processStatusMap.containsKey(processStatusHash))
        {
            return processStatusMap.get(processStatusHash);
        }
        else
        {
            processStatusMap.put(processStatusHash, processStatus);
            return processStatus;
        }
    }
}
