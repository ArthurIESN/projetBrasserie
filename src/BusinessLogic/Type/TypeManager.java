package BusinessLogic.Type;

import DataAccess.Type.TypeDataAccess;
import DataAccess.Type.TypeDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Type.GetAllTypesException;
import Model.Type.Type;

import java.util.ArrayList;

public class TypeManager
{
    private final TypeDataAccess typeDataAccess;

    public TypeManager()
    {
        typeDataAccess = new TypeDBAccess();
    }

    public ArrayList<Type> getAllTypes() throws DatabaseConnectionFailedException, GetAllTypesException
    {
        return typeDataAccess.getAllTypes();
    }
}
