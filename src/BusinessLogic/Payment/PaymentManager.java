package BusinessLogic.Payment;

import DataAccess.Payment.PaymentDBAccess;
import DataAccess.Payment.PaymentDataAccess;

import Model.Payment.Payment;

import java.util.ArrayList;

public class PaymentManager
{
    private final PaymentDataAccess paymentDataAccess = new PaymentDBAccess();

    public ArrayList<Payment> getAllPayments()
    {
        return paymentDataAccess.getAllPayments();
    }

    public Payment getPayment(int id)
    {
        return paymentDataAccess.getPayment(id);
    }

    public void createPayment(Payment payment)
    {
        paymentDataAccess.createPayment(payment);
    }

    public void updatePayment(Payment payment)
    {
        paymentDataAccess.updatePayment(payment);
    }

    public void deletePayment(int id)
    {
        paymentDataAccess.deletePayment(id);
    }


}
