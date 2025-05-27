package BusinessLogic.Process;

import Exceptions.Process.*;
import Model.Process.Process;

import DataAccess.Process.ProcessDBAccess;
import DataAccess.Process.ProcessDataAccess;

import java.util.ArrayList;

public class ProcessManager
{
    private final ProcessDataAccess processDataAccess;

    public ProcessManager()
    {
        processDataAccess = new ProcessDBAccess();
    }

    public ArrayList<Process> getAllProcesses() throws GetAllProcessesException
    {
        return processDataAccess.getAllProcesses();
    }

    public Process getProcess(Integer id) throws GetProcessException
    {
        return processDataAccess.getProcess(id);
    }

    public int createProcess(Process process) throws CreateProcessException
    {
        return processDataAccess.createProcess(process);
    }

    public void deleteProcess(Integer id) throws DeleteProcessException
    {
        processDataAccess.deleteProcess(id);
    }

    public ArrayList<Process> getProcessWithSpecificType(Integer id) throws GetProcessWithSpecificType {
        return processDataAccess.getProcessWithSpecificType(id);
    }

    public void updateProcess(Process process) throws UpdateProcessException
    {
        processDataAccess.updateProcess(process);

    }


}
