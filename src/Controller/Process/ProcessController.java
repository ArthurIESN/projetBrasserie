package Controller.Process;

import BusinessLogic.Process.ProcessManager;
import Controller.AppController;
import Exceptions.Access.UnauthorizedAccessException;
import Exceptions.Process.*;
import Exceptions.Process.CreateProcessException;
import Exceptions.Process.DeleteProcessException;
import Exceptions.Process.GetAllProcessesException;
import Exceptions.Process.GetProcessWithSpecificType;
import Exceptions.Process.UpdateProcessException;
import Model.EmployeeStatus.EmployeeStatus;
import Model.Process.Process;

import java.util.ArrayList;

public class ProcessController {
    private static final ProcessManager processManager = new ProcessManager();

    public static ArrayList<Process> getAllProcesses() throws GetAllProcessesException
    {
        return processManager.getAllProcessess();
    }

    public static Process getProcess(Integer id) throws GetProcessException
    {
        return processManager.getProcess(id);
    }

    public static int createProcess(Process process) throws CreateProcessException
    {
        return processManager.createProcess(process);
    }

    public static void deleteProcess(Integer id) throws DeleteProcessException, UnauthorizedAccessException
    {
        if(!AppController.hasAccess(EmployeeStatus.Status.Manager))
        {
            throw new UnauthorizedAccessException("You don't have the right to delete this process");
        }
        processManager.deleteProcess(id);
    }

    public static ArrayList<Process> getProcessWithSpecificType(Integer id) throws GetProcessWithSpecificType {
        return processManager.getProcessWithSpecificType(id);

    }
      
    public static void updateProcess(Process process) throws UpdateProcessException
    {
        processManager.updateProcess(process);
    }


}
