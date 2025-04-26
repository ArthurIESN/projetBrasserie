package Controller.Process;

import BusinessLogic.Process.ProcessManager;
import Exceptions.Process.CreateProcessException;
import Exceptions.Process.DeleteProcessException;
import Exceptions.Process.GetAllProcessesException;
import Exceptions.Process.UpdateProcessException;
import Model.Process.Process;

import java.util.ArrayList;

public class ProcessController {
    private static final ProcessManager processManager = new ProcessManager();

    // process
    public static ArrayList<Process> getAllProcesses() throws GetAllProcessesException
    {
        return processManager.getAllProcessess();
    }

    public static void createProcess(Process process) throws CreateProcessException
    {
        processManager.createProcess(process);
    }

    public static void deleteProcess(Integer id) throws DeleteProcessException
    {
        processManager.deleteProcess(id);
    }

    public static void updateProcess(Process process) throws UpdateProcessException
    {
        processManager.updateProcess(process);
    }
}
