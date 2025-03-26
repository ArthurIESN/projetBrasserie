package DataAccess.Employee;

import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Employee.GetAllEmployeesException;

import Model.Employee;



public interface EmployeeDataAccess
{
    public ArrayList<Employee> getAllEmployees() throws DatabaseConnectionFailedException, GetAllEmployeesException;
    public Employee getEmployee(int id);
    public void createEmployee(Employee employee);
    public void deleteEmployee(int id);
    public void updateEmployee(Employee employee);
}
