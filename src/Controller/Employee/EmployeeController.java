package Controller.Employee;

import BusinessLogic.Employee.EmployeeManager;
import Controller.AppController;
import Exceptions.Access.UnauthorizedAccessException;
import Exceptions.Employee.*;
import Model.Employee.Employee;
import Model.EmployeeStatus.EmployeeStatus;

import java.util.ArrayList;

public class EmployeeController
{
    private static final EmployeeManager employeeManager = new EmployeeManager();

    public static ArrayList<Employee> getAllEmployees() throws GetAllEmployeesException
    {
        return employeeManager.getAllEmployees();
    }

    public static void createEmployee(Employee employee) throws CreateEmployeeException, UnauthorizedAccessException
    {
        if(!AppController.hasAccess(EmployeeStatus.Status.Manager))
        {
            throw new UnauthorizedAccessException("You don't have permission to update this employee");
        }

        employeeManager.createEmployee(employee);
    }

    public static void deleteEmployee(int id) throws DeleteEmployeeException,  UnauthorizedAccessException
    {
        if(!AppController.hasAccess(EmployeeStatus.Status.Manager))
        {
            throw new UnauthorizedAccessException("You don't have permission to update this employee");
        }

        employeeManager.deleteEmployee(id);
    }

    public static void updateEmployee(Employee employee, String password) throws UpdateEmployeeException, UnauthorizedAccessException
    {
        if(!AppController.hasAccess(EmployeeStatus.Status.Manager))
        {
            throw new UnauthorizedAccessException("You don't have permission to update this employee");
        }

        employeeManager.updateEmployee(employee, password);
    }

    public static Employee getEmployee(int id) throws GetEmployeeException
    {
        return employeeManager.getEmployee(id);
    }

}
