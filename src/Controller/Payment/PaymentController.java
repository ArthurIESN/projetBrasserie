package Controller.Payment;

import BusinessLogic.Payment.PaymentManager;
import Exceptions.Search.GetAllPaymentYearsException;
import Exceptions.Search.SearchPaymentException;
import Model.Payment.Payment;

import java.sql.Date;
import java.util.ArrayList;

public class PaymentController
{
    private static final PaymentManager paymentManager = new PaymentManager();

    public static ArrayList<Payment> getAllPayments()
    {
        return paymentManager.getAllPayments();
    }

    public static ArrayList<Integer> getAllPaymentYears() throws GetAllPaymentYearsException {
        return paymentManager.getAllPaymentYears();
    }

    // Search for payments based on criteria (validated, amount, year)
    public static ArrayList<Payment> searchPayments(String paymentStatus, double minAmount, Date year) throws SearchPaymentException {
        return paymentManager.searchPayments(paymentStatus, minAmount, year);  // Call the Payments Manager
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
