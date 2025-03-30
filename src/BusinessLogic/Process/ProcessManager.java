package BusinessLogic.Process;

import Exceptions.Process.CreateProcessException;
import Model.Process.Process;

import DataAccess.Process.ProcessDBAccess;
import DataAccess.Process.ProcessDataAccess;
import DataAccess.Search.SearchItem.SearchItemDataAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Process.GetAllProcessesException;

import java.util.ArrayList;

public class ProcessManager
{
    private final ProcessDataAccess processDataAccess;

    public ProcessManager()
    {
        processDataAccess = new ProcessDBAccess();
    }

    public ArrayList<Process> getAllProcessess() throws DatabaseConnectionFailedException, GetAllProcessesException
    {
        return processDataAccess.getAllProcesses();
    }

    public void createProcess(Process process) throws DatabaseConnectionFailedException, CreateProcessException
    {
        processDataAccess.createProcess(process);
    }
}
