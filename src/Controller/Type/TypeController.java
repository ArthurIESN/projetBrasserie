package Controller.Type;

import BusinessLogic.ProcessType.ProcessTypeManager;
import Exceptions.ProcessType.GetAllProcessTypesException;
import Model.ProcessType.ProcessType;

import java.util.ArrayList;

public class TypeController {
    private static final ProcessTypeManager typeManager = new ProcessTypeManager();

    // Type
    public static ArrayList<ProcessType> getAllTypes() throws GetAllProcessTypesException
    {
        return typeManager.getAllTypes();
    }
}
