package DataAccess.EmployeeStatus;

import DataAccess.DataAccesUtils;
import Model.EmployeeStatus.EmployeeStatus;
import Model.EmployeeStatus.MakeEmployeeStatus;

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
    public ArrayList<EmployeeStatus> getAllEmployeeStatuses() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static EmployeeStatus makeEmployeeStatus(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "employee_status.id")) return null;

        return MakeEmployeeStatus.getEmployeeStatus(
                resultSet.getInt("employee_status.id"),
                resultSet.getString("employee_status.label")
        );
    }
}
