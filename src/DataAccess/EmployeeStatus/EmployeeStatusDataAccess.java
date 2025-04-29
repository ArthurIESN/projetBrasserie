package DataAccess.EmployeeStatus;

import Model.EmployeeStatus.EmployeeStatus;

import java.util.ArrayList;

public interface EmployeeStatusDataAccess
{
    void createEmployeeStatus(EmployeeStatus employeeStatus);
    void updateEmployeeStatus(EmployeeStatus employeeStatus);
    void deleteEmployeeStatus(int id);
    EmployeeStatus getEmployeeStatus(int id);
    ArrayList<EmployeeStatus> getAllEmployeeStatuses();
}
