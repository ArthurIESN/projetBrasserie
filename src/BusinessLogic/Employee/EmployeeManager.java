package BusinessLogic.Employee;

import java.util.ArrayList;

import DataAccess.Employee.EmployeeDBAccess;
import DataAccess.Employee.EmployeeDataAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Employee.GetAllEmployeesException;
import Model.Employee.Employee;



public class EmployeeManager
{
    private final EmployeeDataAccess employeeDataAccess;

    public EmployeeManager()
    {
        employeeDataAccess = new EmployeeDBAccess();
    }
    public ArrayList<Employee> getAllEmployees() throws DatabaseConnectionFailedException, GetAllEmployeesException
    {
        return employeeDataAccess.getAllEmployees();
    }
}
