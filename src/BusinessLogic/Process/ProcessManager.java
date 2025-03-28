package BusinessLogic.Process;

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
}
