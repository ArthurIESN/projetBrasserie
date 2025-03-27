package Model.Process;

import java.util.HashMap;

public class MakeProcess
{
    private static final HashMap<Integer, Process> processMap = new HashMap<>();

    public static Process getProcess(Integer id, String label, Integer number,
                                     Integer supplierId, String supplierName,
                                     Integer typeId, String typeLabel,
                                     Integer processStatusId, String processStatusLabel)
    {
        if(processMap.containsKey(id))
        {
            return processMap.get(id);
        }
        else
        {
            //Process process = new Process(id, label);
            //processMap.put(id, process);
            return null;
        }
    }
}
