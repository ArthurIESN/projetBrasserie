package DataAccess.EmployeeStatus;

import DataAccess.DataAccessUtils;
import DataAccess.DatabaseConnexion;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Model.EmployeeStatus.EmployeeStatus;
import Model.EmployeeStatus.MakeEmployeeStatus;

import Exceptions.EmployeeStatus.GetAllEmployeeStatusesException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeStatusDBAccess implements EmployeeStatusDataAccess
{
    @Override
    public void createEmployeeStatus(EmployeeStatus employeeStatus) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateEmployeeStatus(EmployeeStatus employeeStatus) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteEmployeeStatus(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public EmployeeStatus getEmployeeStatus(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ArrayList<EmployeeStatus> getAllEmployeeStatuses() throws GetAllEmployeeStatusesException
    {
        String query = "SELECT * FROM employee_status";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            ArrayList<EmployeeStatus> employeeStatuses = new ArrayList<>();

            while (resultSet.next())
            {
                employeeStatuses.add(makeEmployeeStatus(resultSet));
            }

            return employeeStatuses;
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllEmployeeStatusesException("Error while getting all employee statuses");
        }
    }

    public static EmployeeStatus makeEmployeeStatus(ResultSet resultSet) throws SQLException
    {
        if(!DataAccessUtils.hasColumn(resultSet, "employee_status.id")) return null;

        return MakeEmployeeStatus.getEmployeeStatus(
                resultSet.getInt("employee_status.id"),
                resultSet.getString("employee_status.label")
        );
    }
}
