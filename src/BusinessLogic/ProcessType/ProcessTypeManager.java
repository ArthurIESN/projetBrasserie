package BusinessLogic.ProcessType;

import DataAccess.ProcessType.ProcessTypeDataAccess;
import DataAccess.ProcessType.ProcessTypeDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.ProcessType.GetAllProcessTypesException;
import Model.ProcessType.ProcessType;

import java.util.ArrayList;

public class ProcessTypeManager
{
    private final ProcessTypeDataAccess typeDataAccess;

    public ProcessTypeManager()
    {
        typeDataAccess = new ProcessTypeDBAccess();
    }

    public ArrayList<ProcessType> getAllTypes() throws DatabaseConnectionFailedException, GetAllProcessTypesException
    {
        return typeDataAccess.getAllTypes();
    }
}
