package DataAccess.Process;

import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DataAccess.Process.*;

import Model.Process;

public interface ProcessDataAccess
{
    public void createProcess(String label, Integer number, Integer supplierId, Integer typeId, Integer processStatusId, Integer employeeId, Integer customerId) throws DatabaseConnectionFailedException, CreateProcessException;
    public void deleteProcess(Integer id) throws DatabaseConnectionFailedException, DeleteProcessException;
    public void updateProcess(Integer id, String label, Integer number, Integer supplierId, Integer typeId, Integer processStatusId, Integer employeeId, Integer customerId) throws DatabaseConnectionFailedException, UpdateProcessException;
    public Process getProcess(Integer id) throws DatabaseConnectionFailedException, GetProcessException;
    public ArrayList<Process> getAllProcesses() throws DatabaseConnectionFailedException, GetAllProcessesException;
}
