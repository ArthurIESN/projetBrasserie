package Model.Employee;

import java.util.Date;
import java.util.HashMap;

import Model.EmployeeStatus.EmployeeStatus;
import Model.EmployeeStatus.MakeEmployeeStatus;

public class MakeEmployee
{
    private static final HashMap<Integer, Employee> employeeMap = new HashMap<>();

    public static Employee getEmployee(Integer id, String lastName, String firstName, Date birthDate, boolean isMarried, String password, EmployeeStatus employeeStatus)
    {
        int employeeHash = Employee.hashCode(id, lastName, firstName, birthDate, isMarried, employeeStatus);

        if(employeeMap.containsKey(employeeHash))
        {
            return employeeMap.get(employeeHash);
        }
        else
        {
            Employee employee = new Employee(id, lastName, firstName, birthDate, isMarried, password, employeeStatus);
            employeeMap.put(employeeHash, employee);
            return employee;
        }
    }
}
