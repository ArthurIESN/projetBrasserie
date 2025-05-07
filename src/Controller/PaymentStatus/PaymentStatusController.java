package Controller.PaymentStatus;

import BusinessLogic.PaymentStatus.PaymentStatusManager;
import Model.PaymentStatus.PaymentStatus;

import java.util.ArrayList;

public class PaymentStatusController
{
    private static final PaymentStatusManager paymentStatusManager = new PaymentStatusManager();

    public static PaymentStatus getPaymentStatus(int id)
    {
        return paymentStatusManager.getPaymentStatus(id);
    }

    public static void createPaymentStatus(PaymentStatus paymentStatus)
    {
        paymentStatusManager.createPaymentStatus(paymentStatus);
    }

    public static void updatePaymentStatus(PaymentStatus paymentStatus)
    {
        paymentStatusManager.updatePaymentStatus(paymentStatus);
    }

    public static void deletePaymentStatus(int id)
    {
        paymentStatusManager.deletePaymentStatus(id);
    }

    public static ArrayList<PaymentStatus> getAllPaymentStatuses()
    {
        return paymentStatusManager.getAllPaymentStatuses();
    }
}
