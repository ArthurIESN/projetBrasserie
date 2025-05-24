package UI.Employee;

import Model.Employee.Employee;

public interface EmployeeSubject
{
    void addObserver(EmployeeObserver observer);
    void removeObserver(EmployeeObserver observer);
    void notifyObservers(Employee employee);
}
