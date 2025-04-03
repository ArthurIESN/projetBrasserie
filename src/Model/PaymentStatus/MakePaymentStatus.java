package Model.PaymentStatus;

import java.util.HashMap;

public class MakePaymentStatus {
    private static final HashMap<Integer, PaymentStatus> paymentStatusMap = new HashMap<>();

    public static PaymentStatus getPaymentStatus(Integer id, String label)
    {
        if(paymentStatusMap.containsKey(id))
        {
            return paymentStatusMap.get(id);
        }
        else
        {
            PaymentStatus paymentStatus = new PaymentStatus(id, label);
            paymentStatusMap.put(id, paymentStatus);
            return paymentStatus;
        }
    }
}
