package BusinessLogic.ProcessStatus;

import DataAccess.ProcessStatus.ProcessStatusDBAccess;
import DataAccess.ProcessStatus.ProcessStatusDataAccess;

import Exceptions.ProcessStatus.GetAllProcessStatusException;
import Exceptions.ProcessStatus.GetProcessStatusException;
import Model.ProcessStatus.ProcessStatus;

import java.util.ArrayList;

public class ProcessStatusManager
{
    private final ProcessStatusDataAccess processStatusDataAccess = new ProcessStatusDBAccess();

    public ProcessStatusManager()
    {

    }

    public ArrayList<ProcessStatus> getAllProcessStatus() throws GetAllProcessStatusException
    {
        return processStatusDataAccess.getAllProcessStatus();
    }

    public void createProcessStatus(ProcessStatus processStatus)
    {
        processStatusDataAccess.createProcessStatus(processStatus);
    }

    public void updateProcessStatus(ProcessStatus processStatus)
    {
        processStatusDataAccess.updateProcessStatus(processStatus);
    }

    public void deleteProcessStatus(int id)
    {
        processStatusDataAccess.deleteProcessStatus(id);
    }

    public ProcessStatus getProcessStatus(int id) throws GetProcessStatusException
    {
        return processStatusDataAccess.getProcessStatus(id);
    }

    public ArrayList<ProcessStatus> getProcessStatusWithSpecificType(int id)
    {
        return processStatusDataAccess.getProcessStatusWithSpecificType(id);
    }
}
