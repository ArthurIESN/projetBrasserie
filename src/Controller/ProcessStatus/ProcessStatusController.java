package Controller.ProcessStatus;

import BusinessLogic.ProcessStatus.ProcessStatusManager;
import Exceptions.ProcessStatus.GetAllProcessStatusException;
import Model.ProcessStatus.ProcessStatus;

import java.util.ArrayList;

public class ProcessStatusController {
    private static final ProcessStatusManager processStatusManager = new ProcessStatusManager();

    public static ArrayList<ProcessStatus> getAllProcessStatus() throws GetAllProcessStatusException
    {
        return processStatusManager.getAllProcessStatus();
    }

    public static ProcessStatus getProcessStatus(int id)
    {
        return processStatusManager.getProcessStatus(id);
    }

    public static void createProcessStatus(ProcessStatus processStatus)
    {
        processStatusManager.createProcessStatus(processStatus);
    }

    public static void updateProcessStatus(ProcessStatus processStatus)
    {
        processStatusManager.updateProcessStatus(processStatus);
    }

    public static void deleteProcessStatus(int id)
    {
        processStatusManager.deleteProcessStatus(id);
    }

    public static ArrayList<ProcessStatus> getProcessStatusWithSpecificType(int id)
    {
        return processStatusManager.getProcessStatusWithSpecificType(id);
    }
}
