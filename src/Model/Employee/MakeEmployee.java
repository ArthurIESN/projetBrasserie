package Model.Employee;

import java.util.Date;
import java.util.HashMap;

import Model.EmployeeStatus.EmployeeStatus;
import Model.EmployeeStatus.MakeEmployeeStatus;

public class MakeEmployee
{
    private static final HashMap<Integer, Employee> employeeMap = new HashMap<>();

    public static Employee getEmployee(Integer id, String lastName, String firstName, Date birthDate, EmployeeStatus employeeStatus)
    {
        Employee employee = new Employee(id, lastName, firstName, birthDate, employeeStatus);
        int employeeHash = employee.hashCode();

        if(employeeMap.containsKey(employeeHash))
        {
            return employeeMap.get(employeeHash);
        }
        else
        {
            employeeMap.put(employeeHash, employee);
            return employee;
        }
    }
}
