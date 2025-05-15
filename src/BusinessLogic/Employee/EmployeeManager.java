package BusinessLogic.Employee;

import java.time.LocalDate;
import java.util.ArrayList;

import BusinessLogic.Utils.HashUtils;
import DataAccess.Employee.EmployeeDBAccess;
import DataAccess.Employee.EmployeeDataAccess;
import Exceptions.Employee.CreateEmployeeException;
import Exceptions.Employee.DeleteEmployeeException;
import Exceptions.Employee.GetAllEmployeesException;
import Exceptions.Employee.UpdateEmployeeException;
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

    public void createEmployee(Employee employee) throws CreateEmployeeException
    {
        if(isEmployeeInvalid(employee, false, false))
        {
            throw new CreateEmployeeException("Invalid employee data");
        }

        String hashedPassword = HashUtils.hashPassword(employee.getPassword(), 10);
        employee.setPassword(hashedPassword);
        employeeDataAccess.createEmployee(employee);
    }

    public void deleteEmployee(int id) throws DeleteEmployeeException
    {
        if(id <= 0)
        {
            throw new DeleteEmployeeException("Invalid employee ID");
        }

        employeeDataAccess.deleteEmployee(id);
    }

    public void updateEmployee(Employee employee, String password) throws UpdateEmployeeException
    {
        if(isEmployeeInvalid(employee, true, true))
        {
            throw new UpdateEmployeeException("Invalid employee data");
        }

        if(!HashUtils.checkPassword(password, employee.getPassword()))
        {
            throw new UpdateEmployeeException("Invalid password");
        }

        employeeDataAccess.updateEmployee(employee);
    }

    public Employee getEmployee(int id)
    {
        return employeeDataAccess.getEmployee(id);
    }

    public boolean isEmployeeInvalid(Employee employee, boolean confirmId, boolean withoutPassword)
    {
        LocalDate minDate = LocalDate.now().minusYears(18);
        LocalDate maxDate = LocalDate.now().minusYears(100);

        return (confirmId && (employee.getId() == null || employee.getId() <= 0)) ||
                employee.getFirstName() == null || employee.getFirstName().isEmpty() ||
                employee.getLastName() == null || employee.getLastName().isEmpty() ||
                employee.getBirthDate() == null ||
                employee.getBirthDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().isAfter(minDate) || // Too young
                employee.getBirthDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().isBefore(maxDate) || // Too old
                (!withoutPassword && (employee.getPassword() == null || employee.getPassword().isEmpty())) ||
                employee.getEmployeeStatus() == null;
    }
}
