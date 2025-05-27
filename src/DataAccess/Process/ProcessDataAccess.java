package DataAccess.Process;

import java.util.ArrayList;

import Exceptions.Process.*;
import Model.Process.Process;

public interface ProcessDataAccess {
    int createProcess(Process process) throws CreateProcessException;

    void deleteProcess(Integer id) throws DeleteProcessException;

    void updateProcess(Process process) throws UpdateProcessException;

    Process getProcess(Integer id) throws GetProcessException;

    ArrayList<Process> getAllProcesses() throws GetAllProcessesException;

    ArrayList<Process> getProcessWithSpecificType(Integer id) throws GetProcessWithSpecificType;
}