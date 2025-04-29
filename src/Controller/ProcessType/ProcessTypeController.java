package Controller.ProcessType;

import BusinessLogic.ProcessType.ProcessTypeManager;
import Exceptions.ProcessType.GetAllProcessTypesException;
import Model.ProcessType.ProcessType;

import java.util.ArrayList;

public class ProcessTypeController 
{
    private static final ProcessTypeManager processTypeManager = new ProcessTypeManager();
    
    public static ArrayList<ProcessType> getAllTypes() throws GetAllProcessTypesException
    {
        return processTypeManager.getAllTypes();
    }
    
    public static ProcessType getProcessType(int id)
    {
        return processTypeManager.getProcessType(id);
    }
    
    public static void createProcessType(ProcessType processType)
    {
        processTypeManager.createProcessType(processType);
    }
    
    public static void updateProcessType(ProcessType processType)
    {
        processTypeManager.updateProcessType(processType);
    }
    
    public static void deleteProcessType(int id)
    {
        processTypeManager.deleteProcessType(id);
    }
}
