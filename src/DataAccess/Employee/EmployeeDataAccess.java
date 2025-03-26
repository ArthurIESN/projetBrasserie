package DataAccess.Employee;

import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Employee.GetAllEmployeesException;

import Model.Employee;



public interface EmployeeDataAccess
{
    ArrayList<Employee> getAllEmployees() throws DatabaseConnectionFailedException, GetAllEmployeesException;
    Employee getEmployee(int id);
    void createEmployee(Employee employee);
    void deleteEmployee(int id);
    void updateEmployee(Employee employee);
}
