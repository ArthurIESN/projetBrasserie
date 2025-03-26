package DataAccess.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAccess.DatabaseConnexion;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Employee.GetAllEmployeesException;

import Model.Employee;
import Model.EmployeeStatus;

public class EmployeeDBAccess
{
    public ArrayList<Employee> getAllEmployees() throws DatabaseConnectionFailedException, GetAllEmployeesException
    {
        String query = "SELECT *, employee.id_employee_status as id_employee_status " +
                "FROM employee " +
                "JOIN employee_status ON employee.id_employee_status = employee_status.id";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Employee> employees = new ArrayList<>();

            while (resultSet.next())
            {
                employees.add(createEmployeeClass(resultSet));
            }

            return employees;
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllEmployeesException();
        }
    }


    private Employee createEmployeeClass(ResultSet resultSet) throws SQLException
    {
        EmployeeStatus employeeStatus = new EmployeeStatus(
                resultSet.getInt("id_employee_status"),
                resultSet.getString("label"));

        return new Employee(
                resultSet.getInt("id"),
                resultSet.getString("last_name"),
                resultSet.getString("first_name"),
                resultSet.getDate("birth_date"),
                employeeStatus
        );
    }
}
