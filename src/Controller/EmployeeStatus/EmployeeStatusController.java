package Controller.EmployeeStatus;

import BusinessLogic.EmployeeStatus.EmployeeStatusManager;
import Exceptions.EmployeeStatus.GetAllEmployeeStatusesException;
import Model.EmployeeStatus.EmployeeStatus;

import java.util.ArrayList;

public class EmployeeStatusController
{
    private static final EmployeeStatusManager employeeStatusManager = new EmployeeStatusManager();

    public static void createEmployeeStatus(EmployeeStatus employeeStatus)
    {
        employeeStatusManager.createEmployeeStatus(employeeStatus);
    }

    public static void updateEmployeeStatus(EmployeeStatus employeeStatus)
    {
        employeeStatusManager.updateEmployeeStatus(employeeStatus);
    }

    public static void deleteEmployeeStatus(int id)
    {
        employeeStatusManager.deleteEmployeeStatus(id);
    }

    public static EmployeeStatus getEmployeeStatus(int id)
    {
        return employeeStatusManager.getEmployeeStatus(id);
    }

    public static ArrayList<EmployeeStatus> getAllEmployeeStatuses() throws GetAllEmployeeStatusesException
    {
        return employeeStatusManager.getAllEmployeeStatuses();
    }
}
