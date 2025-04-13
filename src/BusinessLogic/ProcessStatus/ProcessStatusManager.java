package BusinessLogic.ProcessStatus;

import DataAccess.ProcessStatus.ProcessStatusDBAccess;
import DataAccess.ProcessStatus.ProcessStatusDataAccess;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.ProcessStatus.GetAllProcessStatusException;
import Model.ProcessStatus.ProcessStatus;

import java.util.ArrayList;

public class ProcessStatusManager
{
    private final ProcessStatusDataAccess processStatusDataAccess;

    public ProcessStatusManager()
    {
        processStatusDataAccess = new ProcessStatusDBAccess();
    }

    public ArrayList<ProcessStatus> getAllProcessStatus() throws GetAllProcessStatusException
    {
        return processStatusDataAccess.getAllProcessStatus();
    }
}
