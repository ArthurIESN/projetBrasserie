package Model.ProcessStatus;

import Exceptions.ProcessStatus.ProcessStatusException;

import java.util.HashMap;

public class MakeProcessStatus
{
    private static final HashMap<Integer, ProcessStatus> processStatusMap = new HashMap<>();

    public static ProcessStatus getProcessStatus(Integer id, String label)
    {
        int processStatusHash = ProcessStatus.hashCode(id, label);

        if(processStatusMap.containsKey(processStatusHash))
        {
            return processStatusMap.get(processStatusHash);
        }
        else
        {
            ProcessStatus processStatus;
            try{
                processStatus = new ProcessStatus(id, label);
                processStatusMap.put(processStatusHash, processStatus);
            }catch (ProcessStatusException e){
                processStatus = null;
                System.err.println("Error creating ProcessStatus: " + e.getMessage());
            }
            return processStatus;
        }
    }
}
