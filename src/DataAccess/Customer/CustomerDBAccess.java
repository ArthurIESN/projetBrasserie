package DataAccess.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAccess.DatabaseConnexion;

import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;

import Model.Customer.Customer;
import Model.Customer.MakeCustomer;
import Model.CustomerStatus.CustomerStatus;
import Model.CustomerStatus.MakeCustomerStatus;

public class CustomerDBAccess implements CustomerDataAccess
{
    public ArrayList<Customer> getAllCustomers() throws GetAllCustomersException
    {
        String query = "SELECT *, customer.num_customer AS id, customer.id_customer_status as id_customer_status, customer_status.label AS customer_status_label " +
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
                CustomerStatus customerStatus = MakeCustomerStatus.getCustomerStatus(
                        resultSet.getInt("id_customer_status"),
                        resultSet.getString("customer_status_label")
                );
                Customer customer = MakeCustomer.getCustomer(
                        resultSet.getInt("num_customer"),
                        resultSet.getString("last_name"),
                        resultSet.getString("first_name"),
                        resultSet.getFloat("credit_limit"),
                        resultSet.getString("num_vat"),
                        customerStatus
                );

                customers.add(customer);
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
