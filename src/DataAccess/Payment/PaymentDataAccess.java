package DataAccess.Payment;

import Model.Payment.Payment;

import java.util.ArrayList;
import java.util.Date;

public interface PaymentDataAccess
{
    ArrayList<Payment> getAllPayments();
    ArrayList<Integer> getAllPaymentYears() throws Exceptions.Search.GetAllPaymentYearsException;
    ArrayList<Payment> searchPayment(String status, float minAmount, Date date) throws Exceptions.Search.SearchPaymentException;

    void createPayment(Payment payment);
    void updatePayment(Payment payment);
    void deletePayment(int id);
    Payment getPayment(int id);
}
