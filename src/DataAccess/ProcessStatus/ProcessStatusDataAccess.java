package DataAccess.ProcessStatus;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.ProcessStatus.GetAllProcessStatusException;
import Model.ProcessStatus.ProcessStatus;

import java.util.ArrayList;

public interface ProcessStatusDataAccess
{
    ArrayList<ProcessStatus> getAllProcessStatus() throws GetAllProcessStatusException;

}
