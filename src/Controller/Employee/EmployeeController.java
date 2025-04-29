package Controller.Employee;

import BusinessLogic.Employee.EmployeeManager;
import Exceptions.Employee.GetAllEmployeesException;
import Model.Employee.Employee;

import java.util.ArrayList;

public class EmployeeController {
    private static final EmployeeManager employeeManager = new EmployeeManager();

    public static ArrayList<Employee> getAllEmployees() throws GetAllEmployeesException
    {
        return employeeManager.getAllEmployees();
    }

    public static void createEmployee(Employee employee)
    {
        employeeManager.createEmployee(employee);
    }

    public static void deleteEmployee(int id)
    {
        employeeManager.deleteEmployee(id);
    }

    public static void updateEmployee(Employee employee)
    {
        employeeManager.updateEmployee(employee);
    }

    public static Employee getEmployee(int id)
    {
        return employeeManager.getEmployee(id);
    }
}
