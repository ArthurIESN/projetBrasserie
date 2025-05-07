package DataAccess.PaymentStatus;

import Model.PaymentStatus.PaymentStatus;

import java.util.ArrayList;

public interface PaymentStatusDataAccess
{
    PaymentStatus getPaymentStatus(int id);
    void createPaymentStatus(PaymentStatus paymentStatus);
    void updatePaymentStatus(PaymentStatus paymentStatus);
    void deletePaymentStatus(int id);
    ArrayList<PaymentStatus> getAllPaymentStatuses();
}
