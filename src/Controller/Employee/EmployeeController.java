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
}
