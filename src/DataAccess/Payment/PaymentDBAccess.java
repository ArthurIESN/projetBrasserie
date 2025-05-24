package DataAccess.Payment;

import DataAccess.Customer.CustomerDBAccess;
import DataAccess.DataAccessUtils;
import DataAccess.DatabaseConnexion;
import DataAccess.Document.DocumentDBAccess;
import DataAccess.PaymentStatus.PaymentStatusDBAccess;
import DataAccess.Process.ProcessDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Document.DocumentException;
import Exceptions.Search.GetAllPaymentYearsException;
import Exceptions.Search.SearchPaymentException;
import Model.Payment.MakePayment;
import Model.Payment.Payment;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

public class PaymentDBAccess implements PaymentDataAccess
{
    public PaymentDBAccess() {
    }

    @Override
    public ArrayList<Payment> getAllPayments() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Payment getPayment(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public ArrayList<Integer> getAllPaymentYears() throws GetAllPaymentYearsException
    {
        String query = "SELECT DISTINCT YEAR(payment_date) AS year FROM payment ORDER BY year DESC";

        ArrayList<Integer> years = new ArrayList<>();
        try {
            Connection databaseConnexion = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                years.add(resultSet.getInt("year"));
            }
        } catch (SQLException | DatabaseConnectionFailedException e) {
            System.err.println("SQL error: " + e.getMessage());
            throw new GetAllPaymentYearsException();
        }

        return years;
    }

    public ArrayList<Payment> searchPayment(String status, float minAmount, Date date) throws SearchPaymentException
    {
        String query =  "SELECT * " +
                "FROM payment " +
                "JOIN document ON payment.id_document = document.id " +
                "JOIN process ON document.id_process = process.id " +
                "JOIN customer ON process.num_customer = customer.num_customer " +
                "JOIN customer_status ON customer.id_customer_status = customer_status.id " +
                "JOIN payment_status ON payment.id_payment_status = payment_status.id " +
                "JOIN process_type ON process.id = process_type.id " +
                "JOIN process_status ON process.id = process_status.id " +
                "WHERE payment.amount >= ? " +
                "AND YEAR(payment.payment_date) = ? " +
                "AND payment_status.label " + (status.equals("Validated") ? "=" : "!=") + " 'Validated';";

        try
        {
            Connection databaseConnexion = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnexion.prepareStatement(query);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            statement.setDouble(1, minAmount);
            statement.setInt(2, calendar.get(Calendar.YEAR));

            ResultSet resultSet = statement.executeQuery();

            ArrayList<Payment> payments = new ArrayList<>();
            while (resultSet.next())
            {
                try
                {
                    Payment payment = makePayment(resultSet);

                    if (payment != null)
                    {
                        payments.add(payment);
                    }
                }
                catch (DocumentException e)
                {
                    System.err.println("Error while creating payment: " + e.getMessage());
                }
            }
            return payments;

        } catch (SQLException | DatabaseConnectionFailedException e) {
            System.err.println("Sql error: " + e.getMessage());
            throw new SearchPaymentException();
        }
    }

    @Override
    public void createPayment(Payment payment) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updatePayment(Payment payment) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deletePayment(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static Payment makePayment(ResultSet resultSet) throws SQLException, DocumentException
    {
        if(!DataAccessUtils.hasColumn(resultSet, "payment.id")) return null;

        return MakePayment.getPayment(
                resultSet.getInt("payment.id"),
                resultSet.getDouble("payment.amount"),
                resultSet.getDate("payment.payment_date"),
                PaymentStatusDBAccess.makePaymentStatus(resultSet),
                DocumentDBAccess.makeDocument(resultSet),
                ProcessDBAccess.makeProcess(resultSet),
                CustomerDBAccess.makeCustomer(resultSet)
        );
    }
}
