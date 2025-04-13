package DataAccess.ProcessType;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.ProcessType.GetAllProcessTypesException;
import Model.ProcessType.ProcessType;

import java.util.ArrayList;

public interface ProcessTypeDataAccess
{
    ArrayList<ProcessType> getAllTypes() throws GetAllProcessTypesException;
}
