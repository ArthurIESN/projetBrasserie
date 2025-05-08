package DataAccess.CustomerStatus;

import Model.CustomerStatus.CustomerStatus;

import DataAccess.DataAccesUtils;
import Model.CustomerStatus.MakeCustomerStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerStatusDBAccess implements CustomerStatusDataAccess
{
    @Override
    public void createCustomerStatus(CustomerStatus customerStatus) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateCustomerStatus(CustomerStatus customerStatus) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteCustomerStatus(Integer id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public CustomerStatus getCustomerStatus(Integer id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ArrayList<CustomerStatus> getAllCustomerStatuses() {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    public static CustomerStatus makeCustomerStatus(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "customer_status.id")) return null;

        return MakeCustomerStatus.getCustomerStatus(
                resultSet.getInt("customer_status.id"),
                resultSet.getString("customer_status.label")
        );
    }
}
