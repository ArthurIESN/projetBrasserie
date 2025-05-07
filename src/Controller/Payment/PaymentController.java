package Controller.Payment;

import BusinessLogic.Payment.PaymentManager;
import Model.Payment.Payment;

import java.util.ArrayList;

public class PaymentController
{
    private static final PaymentManager paymentManager = new PaymentManager();

    public static ArrayList<Payment> getAllPayments()
    {
        return paymentManager.getAllPayments();
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
