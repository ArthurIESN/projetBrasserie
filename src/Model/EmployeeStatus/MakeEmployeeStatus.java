package Model.EmployeeStatus;

import java.util.HashMap;

public class MakeEmployeeStatus
{
    private static final HashMap<Integer, EmployeeStatus> employeeStatusMap = new HashMap<>();

    public static EmployeeStatus getEmployeeStatus(Integer id, String label)
    {
        int employeeStatusHash = EmployeeStatus.hashCode(id, label);

        if(employeeStatusMap.containsKey(employeeStatusHash))
        {
            return employeeStatusMap.get(employeeStatusHash);
        }
        else
        {
            EmployeeStatus employeeStatus = new EmployeeStatus(id, label);
            employeeStatusMap.put(employeeStatusHash, employeeStatus);
            return employeeStatus;
        }
    }
}
