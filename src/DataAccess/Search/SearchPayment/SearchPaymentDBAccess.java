package DataAccess.Search.SearchPayment;

import DataAccess.DatabaseConnexion;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.SearchPaymentException;

import Model.Document.MakeDocument;
import Model.Payment.Payment;
import Model.Payment.MakePayment;
import Model.PaymentStatus.PaymentStatus;
import Model.PaymentStatus.MakePaymentStatus;
import Model.Process.Process;
import Model.Process.MakeProcess;
import Model.Customer.Customer;
import Model.Customer.MakeCustomer;
import Model.Document.Document;
import Model.ProcessType.MakeProcessType;
import Model.ProcessType.ProcessType;

import java.sql.*;
import java.util.ArrayList;


public class SearchPaymentDBAccess implements SearchPaymentDataAccess {
    public SearchPaymentDBAccess() {
    }

    public ArrayList<Payment> searchPayment(String status, double minAmount, Date year) throws SearchPaymentException {
        String query =  "SELECT payment.*, payment_status.label AS payment_status_label, \n" +
                "document.id AS document_id, document.label AS document_label, document.date AS document_date,process.id AS process_id, process.label AS process_label, process_type.id AS id_process_type, customer.num_customer, customer.last_name AS customer_name, customer.first_name AS customer_first_name, customer.credit_limit AS customer_credit_limit, customer.num_VAT AS customer_VAT\n" +
                "FROM payment\n" +
                "JOIN document ON payment.id_document = document.id\n" +
                "JOIN process ON document.id_process = process.id\n" +
                "JOIN customer ON process.num_customer = customer.num_customer\n" +
                "JOIN payment_status ON payment.id_payment_status = payment_status.id\n" +
                "JOIN process_type ON process.id = process_type.id\n" +
                "WHERE payment_status.label = ?" +
                "AND payment.amount > ?" +
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

                Document document = MakeDocument.getDocument(
                        resultSet.getInt("document_id"),
                        resultSet.getString("document_label"),
                        resultSet.getDate("document_date"),
                        null,
                        0.0f,
                        null,
                        null,
                        null,
                        null,
                        0.0f,
                        null,
                        0.0f,
                        0.0f,
                        0.0f,
                        0.0f,
                        null,
                        null,
                        null,
                        null);

                ProcessType processType = MakeProcessType.getProcessType(
                        resultSet.getInt("id_process_type"),
                        null
                );

                Process process = MakeProcess.getProcess(
                        resultSet.getInt("process_id"),
                        resultSet.getString("process_label"),
                        0,
                        null,
                        null,
                        processType,
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
                        document,
                        process,
                        customer
                );
                payments.add(payment);
            }

            return payments;

        } catch (SQLException | DatabaseConnectionFailedException e) {
            System.err.println("Sql error: " + e.getMessage());
            throw new SearchPaymentException();
        }
    }
}
