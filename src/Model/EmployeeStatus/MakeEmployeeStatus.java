package Model.EmployeeStatus;

import java.util.HashMap;

public class MakeEmployeeStatus
{
    private static final HashMap<Integer, EmployeeStatus> employeeStatusMap = new HashMap<>();

    public static EmployeeStatus getEmployeeStatus(Integer id, String label)
    {
        EmployeeStatus employeeStatus = new EmployeeStatus(id, label);
        int employeeStatusHash = employeeStatus.hashCode();

        if(employeeStatusMap.containsKey(employeeStatusHash))
        {
            return employeeStatusMap.get(employeeStatusHash);
        }
        else
        {
            employeeStatusMap.put(employeeStatusHash, employeeStatus);
            return employeeStatus;
        }
    }
}
