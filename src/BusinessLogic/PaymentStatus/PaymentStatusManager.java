package BusinessLogic.PaymentStatus;

import DataAccess.PaymentStatus.PaymentStatusDBAccess;
import DataAccess.PaymentStatus.PaymentStatusDataAccess;
import Model.PaymentStatus.PaymentStatus;

import java.util.ArrayList;

public class PaymentStatusManager
{
    private final PaymentStatusDataAccess paymentStatusDataAccess = new PaymentStatusDBAccess();

    public PaymentStatus getPaymentStatus(int id)
    {
        return paymentStatusDataAccess.getPaymentStatus(id);
    }

    public void createPaymentStatus(PaymentStatus paymentStatus)
    {
        paymentStatusDataAccess.createPaymentStatus(paymentStatus);
    }

    public void updatePaymentStatus(PaymentStatus paymentStatus)
    {
        paymentStatusDataAccess.updatePaymentStatus(paymentStatus);
    }

    public void deletePaymentStatus(int id)
    {
        paymentStatusDataAccess.deletePaymentStatus(id);
    }

    public ArrayList<PaymentStatus> getAllPaymentStatuses()
    {
        return paymentStatusDataAccess.getAllPaymentStatuses();
    }
}
