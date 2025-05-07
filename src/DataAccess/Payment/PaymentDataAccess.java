package DataAccess.Payment;

import Model.Payment.Payment;

import java.util.ArrayList;

public interface PaymentDataAccess
{
    ArrayList<Payment> getAllPayments();
    Payment getPayment(int id);
    void createPayment(Payment payment);
    void updatePayment(Payment payment);
    void deletePayment(int id);
}
