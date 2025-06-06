package DataAccess.ProcessType;

import Exceptions.ProcessType.GetAllProcessTypesException;
import Exceptions.ProcessType.GetProcessTypeException;
import Model.ProcessType.ProcessType;

import java.util.ArrayList;

public interface ProcessTypeDataAccess
{
    ArrayList<ProcessType> getAllTypes() throws GetAllProcessTypesException;

    void createProcessType(ProcessType processType);
    void updateProcessType(ProcessType processType);
    void deleteProcessType(int id);
    ProcessType getProcessType(int id) throws GetProcessTypeException;
}
