package DataAccess.ProcessStatus;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.ProcessStatus.GetAllProcessStatusException;
import Model.ProcessStatus.ProcessStatus;

import java.util.ArrayList;

public interface ProcessStatusDataAccess
{
    ArrayList<ProcessStatus> getAllProcessStatus() throws GetAllProcessStatusException;

    void createProcessStatus(ProcessStatus processStatus);
    void updateProcessStatus(ProcessStatus processStatus);
    void deleteProcessStatus(int id);
    ProcessStatus getProcessStatus(int id);
    ArrayList<ProcessStatus> getProcessStatusWithSpecificType(int id);

}
