package Model.EmployeeStatus;

import java.util.HashMap;

import Exceptions.EmployeeStatus.EmployeeStatusException;

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
            try
            {
                EmployeeStatus employeeStatus = new EmployeeStatus(id, label);
                employeeStatusMap.put(employeeStatusHash, employeeStatus);
                return employeeStatus;
            }
            catch (EmployeeStatusException e)
            {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
