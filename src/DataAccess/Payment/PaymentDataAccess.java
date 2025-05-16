package DataAccess.Payment;

import Model.Payment.Payment;

import java.util.ArrayList;

public interface PaymentDataAccess
{
    ArrayList<Payment> getAllPayments();
    ArrayList<Integer> getAllPaymentYears() throws Exceptions.Search.GetAllPaymentYearsException;
    ArrayList<Payment> searchPayment(String status, double minAmount, java.sql.Date year) throws Exceptions.Search.SearchPaymentException;

    void createPayment(Payment payment);
    void updatePayment(Payment payment);
    void deletePayment(int id);
    Payment getPayment(int id);
}
