package Model.EmployeeStatus;

import java.util.HashMap;

public class MakeEmployeeStatus
{
    private static final HashMap<Integer, EmployeeStatus> employeeStatusMap = new HashMap<>();

    public static EmployeeStatus getEmployeeStatus(Integer id, String label)
    {
        if(employeeStatusMap.containsKey(id))
        {
            return employeeStatusMap.get(id);
        }
        else
        {
            EmployeeStatus employeeStatus = new EmployeeStatus(id, label);
            employeeStatusMap.put(id, employeeStatus);
            return employeeStatus;
        }
    }
}
