package DataAccess.Search.SearchPayment;

import DataAccess.DatabaseConnexion;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.SearchPaymentException;
import Model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SearchPaymentDBAccess implements SearchPaymentDataAccess {

    public SearchPaymentDBAccess() {
    }

    public ArrayList<Payment> searchPayment(boolean isValidated, double minAmount, String year) throws DatabaseConnectionFailedException, SearchPaymentException {
        String query ="SELECT * FROM payment "+
                "JOIN document ON payment.id_document = document.id"+
                "JOIN process ON document.id_process = process.id"+
                "JOIN customer ON process.num_customer = customer.num_customer"+
                "JOIN payment_status ON payment.id_payment_status = payment_status.id"+
                "WHERE payment_status.id IN (?, ?)"+
                "AND payment.amount > ?"+
                "AND YEAR(payment.payment_date) = ?;";
        int statusId = isValidated ? 1 : 2; // Remplacez par les ID réels des statuts

        try (Connection databaseConnexion = DatabaseConnexion.getInstance().getConnection();
             PreparedStatement statement = databaseConnexion.prepareStatement(query)) {

            statement.setInt(1, statusId);
            statement.setInt(2, statusId);
            statement.setDouble(3, minAmount);
            statement.setString(4, year);

            ResultSet resultSet = statement.executeQuery();
            ArrayList<Payment> payments = new ArrayList<>();

            while (resultSet.next()) {
                Payment payment = new Payment(
                        resultSet.getInt("id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("payment_date")
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
