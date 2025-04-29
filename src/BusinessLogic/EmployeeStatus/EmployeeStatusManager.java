package BusinessLogic.EmployeeStatus;

import DataAccess.EmployeeStatus.EmployeeStatusDBAccess;
import DataAccess.EmployeeStatus.EmployeeStatusDataAccess;
import Model.EmployeeStatus.EmployeeStatus;

import java.util.ArrayList;

public class EmployeeStatusManager
{
    private final EmployeeStatusDataAccess employeeStatusDataAccess = new EmployeeStatusDBAccess();

    public void createEmployeeStatus(EmployeeStatus employeeStatus) {
        employeeStatusDataAccess.createEmployeeStatus(employeeStatus);
    }

    public void updateEmployeeStatus(EmployeeStatus employeeStatus) {
        employeeStatusDataAccess.updateEmployeeStatus(employeeStatus);
    }

    public void deleteEmployeeStatus(int id) {
        employeeStatusDataAccess.deleteEmployeeStatus(id);
    }

    public EmployeeStatus getEmployeeStatus(int id) {
        return employeeStatusDataAccess.getEmployeeStatus(id);
    }

    public ArrayList<EmployeeStatus> getAllEmployeeStatuses() {
        return employeeStatusDataAccess.getAllEmployeeStatuses();
    }
}
