package DataAccess.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAccess.DatabaseConnexion;

import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;

import Model.Customer;
import Model.CustomerStatus;

public class CustomerDBAccess
{
    public ArrayList<Customer> getAllCustomers() throws DatabaseConnectionFailedException, GetAllCustomersException
    {
        String query = "SELECT *, customer.num_customer AS id, customer.id_customer_status as id_customer_status, customer_status.label AS customer_status_label " +
                " FROM customer " +
                "JOIN customer_status ON customer.id_customer_status = customer_status.id";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Customer> customers = new ArrayList<>();

            while (resultSet.next())
            {
                customers.add(crateCustomerClass(resultSet));
            }

            return customers;
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllCustomersException();
        }
    }

    private Customer crateCustomerClass(ResultSet resultSet) throws SQLException
    {
        CustomerStatus customerStatus = new CustomerStatus(
                resultSet.getInt("id_customer_status"),
                resultSet.getString("customer_status_label"));

        return new Customer(
                resultSet.getInt("id"),
                resultSet.getString("last_name"),
                resultSet.getString("first_name"),
                resultSet.getFloat("credit_limit"),
                resultSet.getString("num_vat"),
                customerStatus
        );
    }
}
