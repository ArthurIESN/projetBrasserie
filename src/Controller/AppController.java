package Controller;

import BusinessLogic.Employee.EmployeeManager;
import Environement.ConnexionProperties;
import Model.EmployeeStatus.EmployeeStatus;
import Model.Employee.Employee;
import UI.Windows.WindowManager;

public class AppController
{
    private static Employee currentConnectedEmployee = null;
    private static EmployeeManager employeeManager = new EmployeeManager();

    public static Employee getCurrentConnectedEmployee()
    {
        return currentConnectedEmployee;
    }

    public static void autoConnect()
    {
        if(ConnexionProperties.keepConnected())
        {
            System.out.println("Auto connect");

            currentConnectedEmployee = employeeManager.connect(ConnexionProperties.getId(), ConnexionProperties.getPassword());
        }

    }

    public static Employee connect(Integer id, String password)
    {
        currentConnectedEmployee =  employeeManager.connect(id, password);
        WindowManager.onConnect();
        return currentConnectedEmployee;
    }

    public static void disconnect()
    {
        currentConnectedEmployee = null;
        WindowManager.onDisconnect();
    }

    public static boolean hasAccess(EmployeeStatus.Status requiredStatus)
    {
        if(currentConnectedEmployee == null) return false;

        return currentConnectedEmployee.getEmployeeStatus().getLabel().equals(requiredStatus.name());
    }
}
