package DataAccess.Type;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Type.GetAllTypesException;
import Model.Type.Type;

import java.util.ArrayList;

public interface TypeDataAccess
{
    ArrayList<Type> getAllTypes() throws DatabaseConnectionFailedException, GetAllTypesException;
}
