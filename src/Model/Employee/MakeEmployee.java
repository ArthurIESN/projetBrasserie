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
        int employeeHash = Employee.hashCode(id, lastName, firstName, birthDate, employeeStatus);

        if(employeeMap.containsKey(employeeHash))
        {
            return employeeMap.get(employeeHash);
        }
        else
        {
            Employee employee = new Employee(id, lastName, firstName, birthDate, employeeStatus);
            employeeMap.put(employeeHash, employee);
            return employee;
        }
    }
}
