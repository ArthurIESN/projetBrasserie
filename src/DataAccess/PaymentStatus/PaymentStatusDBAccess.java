package DataAccess.PaymentStatus;

import DataAccess.DataAccessUtils;
import Model.PaymentStatus.PaymentStatus;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentStatusDBAccess  implements PaymentStatusDataAccess
{


    @Override
    public PaymentStatus getPaymentStatus(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void createPaymentStatus(PaymentStatus paymentStatus) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updatePaymentStatus(PaymentStatus paymentStatus) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deletePaymentStatus(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ArrayList<PaymentStatus> getAllPaymentStatuses() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static PaymentStatus makePaymentStatus(ResultSet resultSet) throws SQLException
    {
        if(!DataAccessUtils.hasColumn(resultSet, "payment_status.id")) return null;

        return new PaymentStatus(
                resultSet.getInt("payment_status.id"),
                resultSet.getString("payment_status.label")
        );
    }
}
