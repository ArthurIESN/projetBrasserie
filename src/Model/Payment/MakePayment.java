package Model.Payment;

import Model.PaymentStatus.MakePaymentStatus;
import Model.PaymentStatus.PaymentStatus;

import java.sql.Date;
import java.util.HashMap;

public class MakePayment {
    private static final HashMap<Integer, Payment> paymentMap = new HashMap<>();

    public static Payment getPayment(Integer id, double amount, Date paymentDate,int paymentStatusId, String paymentStatusLabel)
    {
        if(paymentMap.containsKey(id))
        {
            return paymentMap.get(id);
        }
        else
        {
            PaymentStatus paymentStatus = MakePaymentStatus.getPaymentStatus(paymentStatusId,paymentStatusLabel);
            Payment payment = new Payment(id, amount, paymentDate, paymentStatus);
            paymentMap.put(id, payment);
            return payment;
        }
    }
}
