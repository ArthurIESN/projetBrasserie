package DataAccess.Employee;

import java.util.ArrayList;

import Exceptions.Employee.*;

import Model.Employee.Employee;



public interface EmployeeDataAccess
{
    ArrayList<Employee> getAllEmployees() throws GetAllEmployeesException;
    Employee getEmployee(int id) throws GetEmployeeException;
    void createEmployee(Employee employee) throws CreateEmployeeException;
    void deleteEmployee(int id) throws DeleteEmployeeException;
    void updateEmployee(Employee employee) throws UpdateEmployeeException;
    Employee connect(Integer id, String password) throws ConnectException;
}
