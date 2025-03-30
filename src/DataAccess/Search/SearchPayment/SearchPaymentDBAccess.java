package DataAccess.Search.SearchPayment;

import DataAccess.DatabaseConnexion;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.SearchPaymentException;
import Model.Payment.Payment;
import Model.PaymentStatus.PaymentStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SearchPaymentDBAccess implements SearchPaymentDataAccess {

    private final Map<Integer, PaymentStatus> paymentStatusCache = new HashMap<>();

    public SearchPaymentDBAccess() {
    }

    public ArrayList<Payment> searchPayment(String status, double minAmount, Date year) throws DatabaseConnectionFailedException, SearchPaymentException {
        String query = "SELECT * FROM payment " +
                "JOIN document ON payment.id_document = document.id " +
                "JOIN process ON document.id_process = process.id " +
                "JOIN customer ON process.num_customer = customer.num_customer " +
                "JOIN payment_status ON payment.id_payment_status = payment_status.id " +
                "WHERE payment_status.id IN (?) " +
                "AND payment.amount > ? " +
                "AND YEAR(payment.payment_date) = ?;";

        try (Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();
             PreparedStatement statement = databaseConnexion.prepareStatement(query)) {

            statement.setString(1, status);
            statement.setDouble(2, minAmount);
            statement.setDate(3, year);

            ResultSet resultSet = statement.executeQuery();
            ArrayList<Payment> payments = new ArrayList<>();

            while (resultSet.next()) {
                PaymentStatus paymentStatus;

                if (paymentStatusCache.containsKey(statement.getResultSet().getInt("id_payment_status"))) {
                    paymentStatus = paymentStatusCache.get(statement.getResultSet().getInt("id_payment_status"));
                } else {
                    paymentStatus = new PaymentStatus(
                            resultSet.getInt("id_payment_status"),
                            resultSet.getString("label")
                    );
                    paymentStatusCache.put(statement.getResultSet().getInt("id_payment_status"), paymentStatus);
                }

                Payment payment = new Payment(
                        resultSet.getInt("id"),
                        resultSet.getDouble("amount"),
                        resultSet.getDate("payment_date"),
                        paymentStatus
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
