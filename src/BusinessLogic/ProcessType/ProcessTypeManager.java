package BusinessLogic.ProcessType;

import DataAccess.ProcessType.ProcessTypeDataAccess;
import DataAccess.ProcessType.ProcessTypeDBAccess;
import Exceptions.ProcessType.GetAllProcessTypesException;
import Exceptions.ProcessType.GetProcessTypeException;
import Model.ProcessType.ProcessType;

import java.util.ArrayList;

public class ProcessTypeManager
{
    private final ProcessTypeDataAccess typeDataAccess;

    public ProcessTypeManager()
    {
        typeDataAccess = new ProcessTypeDBAccess();
    }

    public ArrayList<ProcessType> getAllTypes() throws GetAllProcessTypesException
    {
        return typeDataAccess.getAllTypes();
    }

    public void createProcessType(ProcessType processType)
    {
        typeDataAccess.createProcessType(processType);
    }

    public void updateProcessType(ProcessType processType)
    {
        typeDataAccess.updateProcessType(processType);
    }

    public void deleteProcessType(int id)
    {
        typeDataAccess.deleteProcessType(id);
    }

    public ProcessType getProcessType(int id) throws GetProcessTypeException
    {
        return typeDataAccess.getProcessType(id);
    }
}
