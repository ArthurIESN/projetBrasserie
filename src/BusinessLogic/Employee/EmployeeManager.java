package BusinessLogic.Employee;

import java.util.ArrayList;

import BusinessLogic.Utils.HashUtils;
import DataAccess.Employee.EmployeeDBAccess;
import DataAccess.Employee.EmployeeDataAccess;
import Exceptions.Employee.GetAllEmployeesException;
import Model.Employee.Employee;


public class EmployeeManager
{
    private final EmployeeDataAccess employeeDataAccess = new EmployeeDBAccess();

    public EmployeeManager()
    {

    }

    public Employee connect(Integer id, String password)
    {
        return employeeDataAccess.connect(id, password);
    }

    public ArrayList<Employee> getAllEmployees() throws GetAllEmployeesException
    {
        return employeeDataAccess.getAllEmployees();
    }

    public void createEmployee(Employee employee)
    {
        employeeDataAccess.createEmployee(employee);
    }

    public void deleteEmployee(int id)
    {
        employeeDataAccess.deleteEmployee(id);
    }

    public void updateEmployee(Employee employee)
    {
        employeeDataAccess.updateEmployee(employee);
    }

    public Employee getEmployee(int id)
    {
        return employeeDataAccess.getEmployee(id);
    }
}
