package BusinessLogic.Payment;

import DataAccess.Payment.PaymentDBAccess;
import DataAccess.Payment.PaymentDataAccess;

import Exceptions.Search.GetAllPaymentYearsException;
import Exceptions.Payment.GetAllPaymentException;
import Exceptions.Search.SearchPaymentException;
import Model.Payment.Payment;

import java.util.Date;
import java.util.ArrayList;

public class PaymentManager
{
    private final PaymentDataAccess paymentDBAccess = new PaymentDBAccess();
    public PaymentManager() {}

    public ArrayList<Payment> getAllPayments()
    {
        return paymentDBAccess.getAllPayments();
    }

    public ArrayList<Payment> searchPayments(String status, float minAmount, Date date) throws SearchPaymentException
    {
        return paymentDBAccess.searchPayment(status, minAmount, date);
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
