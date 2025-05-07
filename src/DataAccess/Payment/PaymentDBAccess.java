package DataAccess.Payment;

import DataAccess.Customer.CustomerDBAccess;
import DataAccess.DataAccesUtils;
import DataAccess.Document.DocumentDBAccess;
import DataAccess.PaymentStatus.PaymentStatusDBAccess;
import DataAccess.Process.ProcessDBAccess;
import Model.Payment.MakePayment;
import Model.Payment.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDBAccess implements PaymentDataAccess
{
    @Override
    public ArrayList<Payment> getAllPayments() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Payment getPayment(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
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

    public static Payment makePayment(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "payment.id")) return null;

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
