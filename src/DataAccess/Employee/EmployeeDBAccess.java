package DataAccess.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BusinessLogic.Utils.HashUtils;
import DataAccess.DataAccesUtils;
import DataAccess.DatabaseConnexion;
import DataAccess.EmployeeStatus.EmployeeStatusDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Employee.GetAllEmployeesException;

import Model.CustomerStatus.MakeCustomerStatus;
import Model.Employee.Employee;
import Model.Employee.MakeEmployee;
import Model.EmployeeStatus.EmployeeStatus;
import Model.EmployeeStatus.MakeEmployeeStatus;

public class EmployeeDBAccess implements EmployeeDataAccess
{
    public ArrayList<Employee> getAllEmployees() throws GetAllEmployeesException
    {
        String query = "SELECT *, employee.id_employee_status as id_employee_status " +
                "FROM employee " +
                "JOIN employee_status ON employee.id_employee_status = employee_status.id";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Employee> employees = new ArrayList<>();

            while (resultSet.next())
            {
                employees.add(makeEmployee(resultSet));
            }

            return employees;
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllEmployeesException();
        }
    }

    //@todo : handle exception
    public Employee getEmployee(int id)
    {
        String query = "SELECT * " +
                "FROM employee " +
                "JOIN employee_status ON employee.id_employee_status = employee_status.id " +
                "WHERE employee.id = ?";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                return makeEmployee(resultSet);
            }
            else
            {
                System.out.println("No employee found with id: " + id);
                return null;
            }
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void createEmployee(Employee employee) {

    }

    public void deleteEmployee(int id) {

    }

    public void updateEmployee(Employee employee) {

    }

    //@todo : handle exception
    @Override
    public Employee connect(Integer id, String password)
    {
        String queryPassword = "SELECT password " +
                "FROM employee " +
                "WHERE id = ?";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(queryPassword);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                String hashedPassword = resultSet.getString("password");

                if(HashUtils.checkPassword(password, hashedPassword))
                {
                    return getEmployee(id);
                }
                else
                {
                    System.out.println("Password is incorrect");
                    return null;
                }
            }
            else
            {
                System.out.println("No employee found with id: " + id);
                return null;
            }
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            return null;
        }
    }


    public static Employee makeEmployee(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "employee.id")) return null;

        return MakeEmployee.getEmployee(
                resultSet.getInt("employee.id"),
                resultSet.getString("employee.last_name"),
                resultSet.getString("employee.first_name"),
                resultSet.getDate("employee.birth_date"),
                resultSet.getString("employee.password"),
                EmployeeStatusDBAccess.makeEmployeeStatus(resultSet)
        );
    }
}
