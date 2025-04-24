package Controller.ProcessStatus;

import BusinessLogic.ProcessStatus.ProcessStatusManager;
import Exceptions.ProcessStatus.GetAllProcessStatusException;
import Model.ProcessStatus.ProcessStatus;

import java.util.ArrayList;

public class ProcessStatusController {
    private static final ProcessStatusManager processStatusManager = new ProcessStatusManager();

    // process status
    public static ArrayList<ProcessStatus> getAllProcessStatus() throws GetAllProcessStatusException
    {
        return processStatusManager.getAllProcessStatus();
    }
}
