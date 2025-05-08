package UI.Employee;

import Model.Employee.Employee;
import Model.Process.Process;

public interface EmployeeObserver
{
    void update(Employee employee);
}
