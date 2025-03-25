package DataAccess.Customer;

import DataAccess.DatabaseConnexion;
import Model.Customer;
import Model.CustomerStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDBAccess
{
    public ArrayList<Customer> getAllCustomers()
    {
        String query = "SELECT *, customer.num_customer AS id, customer.id_customer_status as id_customer_status" +
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
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private Customer crateCustomerClass(ResultSet resultSet)
    {
        try
        {
            CustomerStatus customerStatus = new CustomerStatus(
                    resultSet.getInt("id_customer_status"),
                    resultSet.getString("label_customer_status"));

            return new Customer(
                    resultSet.getInt("id"),
                    resultSet.getString("last_name"),
                    resultSet.getString("first_name"),
                    resultSet.getFloat("credit_limit"),
                    resultSet.getString("num_vat"),
                    customerStatus
            );


        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
            //@todo : throw a custom exception
            return null;
        }
    }
}
