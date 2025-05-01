package DataAccess.Employee;

import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Employee.CreateEmployeeException;
import Exceptions.Employee.DeleteEmployeeException;
import Exceptions.Employee.GetAllEmployeesException;

import Exceptions.Employee.UpdateEmployeeException;
import Model.Employee.Employee;



public interface EmployeeDataAccess
{
    ArrayList<Employee> getAllEmployees() throws GetAllEmployeesException;
    Employee getEmployee(int id);
    void createEmployee(Employee employee) throws CreateEmployeeException;
    void deleteEmployee(int id) throws DeleteEmployeeException;
    void updateEmployee(Employee employee) throws UpdateEmployeeException;
    Employee connect(Integer id, String password);
}
