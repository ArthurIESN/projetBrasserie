package DataAccess.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAccess.DataAccessUtils;
import DataAccess.DatabaseConnexion;
import DataAccess.CustomerStatus.CustomerStatusDBAccess;

import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;

import Model.Customer.Customer;
import Model.Customer.MakeCustomer;

public class CustomerDBAccess implements CustomerDataAccess
{
    public ArrayList<Customer> getAllCustomers() throws GetAllCustomersException
    {
        String query = "SELECT * " +
                " FROM customer " +
                "JOIN customer_status ON customer.id_customer_status = customer_status.id";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();

            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Customer> customers = new ArrayList<>();

            while (resultSet.next())
            {
                customers.add(makeCustomer(resultSet));
            }

            return customers;
        }
        catch (SQLException | DatabaseConnectionFailedException e)
        {
            System.err.println(e.getMessage());
            throw new GetAllCustomersException();
        }
    }

    public Customer getCustomer(int id) {
        return null;
    }

    public void createCustomer(Customer customer) {

    }

    public void deleteCustomer(int id) {

    }

    public void updateCustomer(Customer customer) {

    }

    public static Customer makeCustomer(ResultSet resultSet) throws SQLException
    {
        if(!DataAccessUtils.hasColumn(resultSet, "customer.num_customer")) return null;

        return MakeCustomer.getCustomer(
                resultSet.getInt("customer.num_customer"),
                resultSet.getString("customer.last_name"),
                resultSet.getString("customer.first_name"),
                resultSet.getFloat("customer.credit_limit"),
                resultSet.getString("customer.num_vat"),
                CustomerStatusDBAccess.makeCustomerStatus(resultSet)
        );
    }
}
