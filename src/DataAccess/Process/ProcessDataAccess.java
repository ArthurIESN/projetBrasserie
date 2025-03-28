package DataAccess.Process;

import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;

import Exceptions.Process.*;
import Model.Process.Process;

public interface ProcessDataAccess
{
    void createProcess(String label, Integer number, Integer supplierId, Integer typeId, Integer processStatusId, Integer employeeId, Integer customerId) throws DatabaseConnectionFailedException, CreateProcessException;
    void deleteProcess(Integer id) throws DatabaseConnectionFailedException, DeleteProcessException;
    void updateProcess(Integer id, String label, Integer number, Integer supplierId, Integer typeId, Integer processStatusId, Integer employeeId, Integer customerId) throws DatabaseConnectionFailedException, UpdateProcessException;
    Process getProcess(Integer id) throws DatabaseConnectionFailedException, GetProcessException;
    ArrayList<Process> getAllProcesses() throws DatabaseConnectionFailedException, GetAllProcessesException;
}
