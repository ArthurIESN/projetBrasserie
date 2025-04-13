package DataAccess.Process;

import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;

import Exceptions.Process.*;
import Model.Process.Process;

public interface ProcessDataAccess
{
    void createProcess(Process process) throws  CreateProcessException;
    void deleteProcess(Integer id) throws  DeleteProcessException;
    void updateProcess(Integer id, String label, Integer number, Integer supplierId, Integer typeId, Integer processStatusId, Integer employeeId, Integer customerId) throws  UpdateProcessException;
    Process getProcess(Integer id) throws GetProcessException;
    ArrayList<Process> getAllProcesses() throws GetAllProcessesException;
}
