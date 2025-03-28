package Model.Employee;

import java.util.Date;
import java.util.HashMap;

import Model.EmployeeStatus.EmployeeStatus;
import Model.EmployeeStatus.MakeEmployeeStatus;

public class MakeEmployee
{
    private static final HashMap<Integer, Employee> employeeMap = new HashMap<>();

    public static Employee getEmployee(Integer id, String lastName, String firstName, Date birthDate, Integer employeeStatusId, String employeeStatusLabel)
    {
        if(employeeMap.containsKey(id))
        {
            return employeeMap.get(id);
        }
        else
        {
            EmployeeStatus employeeStatus = MakeEmployeeStatus.getEmployeeStatus(employeeStatusId, employeeStatusLabel);
            Employee employee = new Employee(id, lastName, firstName, birthDate, employeeStatus);
            employeeMap.put(id, employee);
            return employee;
        }
    }
}
