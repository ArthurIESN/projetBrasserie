package Controller.Payment;

import BusinessLogic.Payment.PaymentManager;
import Exceptions.Search.GetAllPaymentYearsException;
import Exceptions.Search.SearchPaymentException;
import Model.Payment.Payment;

import java.util.Date;
import java.util.ArrayList;

public class PaymentController
{
    private static final PaymentManager paymentManager = new PaymentManager();

    public static ArrayList<Payment> getAllPayments()
    {
        return paymentManager.getAllPayments();
    }

    public static ArrayList<Integer> getAllPaymentYears() throws GetAllPaymentYearsException
    {
        return paymentManager.getAllPaymentYears();
    }

    // Search for payments based on criteria (validated, amount, year)
    public static ArrayList<Payment> searchPayments(String paymentStatus, float minAmount, Date date) throws SearchPaymentException
    {
        return paymentManager.searchPayments(paymentStatus, minAmount, date);
    }

    public static Payment getPayment(int id)
    {
        return paymentManager.getPayment(id);
    }

    public static void createPayment(Payment payment)
    {
        paymentManager.createPayment(payment);
    }

    public static void updatePayment(Payment payment)
    {
        paymentManager.updatePayment(payment);
    }

    public static void deletePayment(int id)
    {
        paymentManager.deletePayment(id);
    }
}
