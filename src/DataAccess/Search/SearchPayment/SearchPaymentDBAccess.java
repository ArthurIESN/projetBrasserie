package DataAccess.Search.SearchPayment;

import DataAccess.DatabaseConnexion;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.SearchPaymentException;

import Model.Payment.Payment;
import Model.Payment.MakePayment;
import Model.PaymentStatus.PaymentStatus;
import Model.PaymentStatus.MakePaymentStatus;
import Model.Process.Process;
import Model.Process.MakeProcess;
import Model.Customer.Customer;
import Model.Customer.MakeCustomer;
import Model.Document.Document;

import java.sql.*;
import java.util.ArrayList;


public class SearchPaymentDBAccess implements SearchPaymentDataAccess {
    public SearchPaymentDBAccess() {
    }

    public ArrayList<Payment> searchPayment(String status, double minAmount, Date year) throws DatabaseConnectionFailedException, SearchPaymentException {
        String query =  "SELECT payment.*, payment_status.label AS payment_status_label, " +
                "document.id AS document_id, document.label AS document_label, document.date AS document_date, " +
                "process.id AS process_id, process.label AS process_label, process.id_process_type AS process_type, " +
                "customer.num_customer, customer.last_name AS customer_name, customer.first_name AS customer_first_name, " +
                "customer.credit_limit AS customer_credit_limit, customer.num_VAT AS customer_VAT " +
                "FROM payment " +
                "JOIN document ON payment.id_document = document.id " +
                "JOIN process ON document.id_process = process.id " +
                "JOIN customer ON process.num_customer = customer.num_customer " +
                "JOIN payment_status ON payment.id_payment_status = payment_status.id " +
                "WHERE payment_status.label = ? " +
                "AND payment.amount > ? " +
                "AND YEAR(payment.payment_date) = ?;";

        try {
            Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();
            PreparedStatement statement = databaseConnexion.prepareStatement(query);

            statement.setString(1, status);
            statement.setDouble(2, minAmount);
            statement.setInt(3, year.toLocalDate().getYear());

            ResultSet resultSet = statement.executeQuery();

ArrayList<Payment> payments = new ArrayList<>();
            while (resultSet.next()) {
                PaymentStatus paymentStatus = MakePaymentStatus.getPaymentStatus(
                        resultSet.getInt("id_payment_status"),
                        resultSet.getString("payment_status_label"));

                Process process = MakeProcess.getProcess(
                        resultSet.getInt("process_id"),
                        resultSet.getString("process_label"),
                        0,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

                Customer customer = MakeCustomer.getCustomer(
                        resultSet.getInt("num_customer"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_first_name"),
                        resultSet.getInt("customer_credit_limit"),
                        resultSet.getString("customer_VAT"),
                        null);

                Payment payment = MakePayment.getPayment(
                        resultSet.getInt("id"),
                        resultSet.getDouble("amount"),
                        resultSet.getDate("payment_date"),
                        paymentStatus,
                        process,
                        customer
                );
                payments.add(payment);
            }

            return payments;

        } catch (SQLException e) {
            System.err.println("Sql error: " + e.getMessage());
            throw new SearchPaymentException();
        }
    }
}
