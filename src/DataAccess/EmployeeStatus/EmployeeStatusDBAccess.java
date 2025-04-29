package DataAccess.EmployeeStatus;

import DataAccess.DataAccesUtils;
import Model.EmployeeStatus.EmployeeStatus;
import Model.EmployeeStatus.MakeEmployeeStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeStatusDBAccess implements EmployeeStatusDataAccess
{


    public static EmployeeStatus makeEmployeeStatus(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "employee_status.id")) return null;

        return MakeEmployeeStatus.getEmployeeStatus(
                resultSet.getInt("employee_status.id"),
                resultSet.getString("employee_status.label")
        );
    }
}
