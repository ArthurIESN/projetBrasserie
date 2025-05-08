package UI.Employee;

import Model.Employee.Employee;
import Model.Process.Process;
import UI.Process.ProcessObserver;

public interface EmployeeSubject
{
    void addObserver(EmployeeObserver observer);
    void removeObserver(EmployeeObserver observer);
    void notifyObservers(Employee employee);
}
