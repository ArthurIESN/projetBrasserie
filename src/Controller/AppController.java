package Controller;

import BusinessLogic.Employee.EmployeeManager;
import Environement.ConnexionProperties;
import Exceptions.Employee.ConnectException;
import Model.EmployeeStatus.EmployeeStatus;
import Model.Employee.Employee;
import UI.Windows.WindowManager;

public class AppController
{
    private static Employee currentConnectedEmployee = null;
    private static final EmployeeManager employeeManager = new EmployeeManager();

    public static Employee getCurrentConnectedEmployee()
    {
        return currentConnectedEmployee;
    }

    public static void autoConnect()
    {
        if(ConnexionProperties.keepConnected())
        {
            System.out.println("Auto connect");

            try
            {
                currentConnectedEmployee = employeeManager.connect(ConnexionProperties.getId(), ConnexionProperties.getPassword());
            }
            catch (ConnectException e)
            {
                System.out.println("Auto connect failed : " + e.getMessage());
            }


        }

    }

    public static Employee connect(Integer id, String password) throws ConnectException
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
