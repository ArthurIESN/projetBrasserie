package BusinessLogic.Process;

import Controller.AppController;
import Exceptions.Process.*;
import Exceptions.ProcessType.GetAllProcessTypesException;
import Model.Process.Process;

import DataAccess.Process.ProcessDBAccess;
import DataAccess.Process.ProcessDataAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Model.ProcessType.ProcessType;

import java.util.ArrayList;

public class ProcessManager
{
    private final ProcessDataAccess processDataAccess;

    public ProcessManager()
    {
        processDataAccess = new ProcessDBAccess();
    }

    public ArrayList<Process> getAllProcessess() throws GetAllProcessesException
    {
        return processDataAccess.getAllProcesses();
    }

    public Process getProcess(Integer id) throws GetProcessException
    {
        return processDataAccess.getProcess(id);
    }

    public void createProcess(Process process) throws CreateProcessException
    {
        processDataAccess.createProcess(process);
    }

    public void deleteProcess(Integer id) throws DeleteProcessException
    {
        processDataAccess.deleteProcess(id);
    }

    public void updateProcess(Process process) throws UpdateProcessException
    {
        processDataAccess.updateProcess(process);
    }

    public ArrayList<Process> getProcessWithSpecificType(Integer id) throws GetProcessWithSpecificType
    {
        return processDataAccess.getProcessWithSpecificType(id);
    }
}
