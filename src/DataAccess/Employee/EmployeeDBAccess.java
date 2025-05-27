package DataAccess.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BusinessLogic.Utils.HashUtils;
import DataAccess.DataAccessUtils;
import DataAccess.DatabaseConnexion;
import DataAccess.EmployeeStatus.EmployeeStatusDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Employee.*;

import Model.Employee.Employee;
import Model.Employee.MakeEmployee;

public class EmployeeDBAccess implements EmployeeDataAccess
{
    public ArrayList<Employee> getAllEmployees() throws GetAllEmployeesException
    {
        String query = "SELECT *, employee.id_employee_status as id_employee_status " +
                "FROM employee " +
                "JOIN employee_status ON employee.id_employee_status = employee_status.id " +
                "ORDER BY employee.id";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Employee> employees = new ArrayList<>();

            while (resultSet.next())
            {
                Employee employee = EmployeeDBAccess.makeEmployee(resultSet);

                if (employee != null)
                {
                    employees.add(employee);
                }
            }

            return employees;
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllEmployeesException();
        }
    }

    public Employee getEmployee(int id) throws GetEmployeeException
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
                throw new GetEmployeeException("No employee found with this id");
            }
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetEmployeeException("Error while getting employee");
        }
    }

    public void createEmployee(Employee employee) throws CreateEmployeeException
    {
        if(employee == null)
        {
            throw new CreateEmployeeException("Employee cannot be null");
        }
        else if(employee.getEmployeeStatus() == null)
        {
            throw new CreateEmployeeException("Employee status cannot be null");
        }
        else if(employee.getBirthDate() == null)
        {
            throw new CreateEmployeeException("Birth date cannot be null");
        }


        String query = "INSERT INTO employee (last_name, first_name, birth_date, password, id_employee_status) " +
                "VALUES (?, ?, ?, ?, ?)";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setString(1, employee.getLastName());
            statement.setString(2, employee.getFirstName());
            statement.setDate(3, new java.sql.Date(employee.getBirthDate().getTime()));
            statement.setString(4, employee.getPassword());
            statement.setInt(5, employee.getEmployeeStatus().getId());

            statement.executeUpdate();
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new CreateEmployeeException("Error while creating employee");
        }
    }

    public void deleteEmployee(int id) throws DeleteEmployeeException
    {
        String query = "DELETE FROM employee " +
                "WHERE id = ?";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setInt(1, id);

            int rowCount = statement.executeUpdate();

            if (rowCount == 0)
            {
                throw new DeleteEmployeeException("No employee found with id: " + id);
            }
        }
        catch (SQLException  e)
        {
            System.err.println(e.getMessage());

            if(DataAccessUtils.isASQLForeignKeyConstraintFails(e.getErrorCode()))
            {
                throw new DeleteEmployeeException("Cannot delete employee. This employee is linked to an other entity");
            }

            throw new DeleteEmployeeException("Error while deleting employee");
        }
        catch (DatabaseConnectionFailedException e)
        {
            System.out.println(e.getMessage());
            throw new DeleteEmployeeException("Error while deleting employee");
        }


    }

    public void updateEmployee(Employee employee) throws UpdateEmployeeException
    {
        if(employee == null)
        {
            throw new UpdateEmployeeException("Employee cannot be null");
        }
        else if(employee.getEmployeeStatus() == null)
        {
            throw new UpdateEmployeeException("Employee status cannot be null");
        }
        else if(employee.getBirthDate() == null)
        {
            throw new UpdateEmployeeException("Birth date cannot be null");
        }

        String query = "UPDATE employee " +
                "SET last_name = ?, first_name = ?, birth_date = ?, id_employee_status = ? " +
                "WHERE id = ?";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            statement.setString(1, employee.getLastName());
            statement.setString(2, employee.getFirstName());
            statement.setDate(3, new java.sql.Date(employee.getBirthDate().getTime()));
            statement.setInt(4, employee.getEmployeeStatus().getId());
            statement.setInt(5, employee.getId());

            int rowCount = statement.executeUpdate();

            if (rowCount == 0)
            {
                throw new UpdateEmployeeException("No employee found with id: " + employee.getId());
            }
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new UpdateEmployeeException("Error while updating employee");
        }
    }

    @Override
    public Employee connect(Integer id, String password) throws ConnectException
    {

        if(id == null)
        {
            throw new ConnectException("ID cannot be null");
        }
        else if(password == null)
        {
            throw new ConnectException("Password cannot be null");
        }

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
                    throw new ConnectException("Password is incorrect");
                }
            }
            else
            {
                System.out.println("No employee found with id: " + id);
                throw new ConnectException("No employee found with this id");
            }
        }
        catch (SQLException | GetEmployeeException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new ConnectException("Error while connecting employee");
        }
    }


    public static Employee makeEmployee(ResultSet resultSet) throws SQLException
    {
        if(!DataAccessUtils.hasColumn(resultSet, "employee.id")) return null;

        return MakeEmployee.getEmployee(
                resultSet.getInt("employee.id"),
                resultSet.getString("employee.last_name"),
                resultSet.getString("employee.first_name"),
                resultSet.getDate("employee.birth_date"),
                resultSet.getBoolean("employee.is_married"),
                resultSet.getString("employee.password"),
                EmployeeStatusDBAccess.makeEmployeeStatus(resultSet)
        );
    }
}
