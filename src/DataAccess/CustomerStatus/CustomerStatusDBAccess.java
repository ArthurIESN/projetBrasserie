package DataAccess.CustomerStatus;

import Model.CustomerStatus.CustomerStatus;

import DataAccess.DataAccesUtils;
import Model.CustomerStatus.MakeCustomerStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerStatusDBAccess implements CustomerStatusDataAccess
{


    public static CustomerStatus makeCustomerStatus(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "customer_status.id")) return null;

        return MakeCustomerStatus.getCustomerStatus(
                resultSet.getInt("customer_status.id"),
                resultSet.getString("customer_status.label")
        );
    }
}
