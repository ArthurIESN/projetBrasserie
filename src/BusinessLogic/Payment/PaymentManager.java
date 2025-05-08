package BusinessLogic.Payment;

import DataAccess.Payment.PaymentDBAccess;
import DataAccess.Payment.PaymentDataAccess;

import Exceptions.Search.GetAllPaymentYearsException;
import Exceptions.Payment.GetAllPaymentException;
import Exceptions.Search.SearchPaymentException;
import Model.Payment.Payment;

import java.sql.Date;
import java.util.ArrayList;

public class PaymentManager
{
    private final PaymentDataAccess paymentDBAccess = new PaymentDBAccess();
    public PaymentManager() {}

    public ArrayList<Payment> getAllPayments() throws GetAllPaymentException
    {
        return paymentDBAccess.getAllPayments();
    }

    public ArrayList<Payment> searchPayments(String status, double minAmount, Date year) throws SearchPaymentException {
        ArrayList<Payment> Payments;

        Payments = paymentDBAccess.searchPayment(status, minAmount, year);

        return Payments;
    }

    public ArrayList<Integer> getAllPaymentYears() throws GetAllPaymentYearsException {
        return paymentDBAccess.getAllPaymentYears();
    }

    public Payment getPayment(int id)
    {
        return paymentDBAccess.getPayment(id);
    }

    public void createPayment(Payment payment)
    {
        paymentDBAccess.createPayment(payment);
    }

    public void updatePayment(Payment payment)
    {
        paymentDBAccess.updatePayment(payment);
    }

    public void deletePayment(int id)
    {
        paymentDBAccess.deletePayment(id);
    }

}
