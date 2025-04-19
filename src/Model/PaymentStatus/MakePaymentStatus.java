package Model.PaymentStatus;

import java.util.HashMap;

public class MakePaymentStatus {
    private static final HashMap<Integer, PaymentStatus> paymentStatusMap = new HashMap<>();

    public static PaymentStatus getPaymentStatus(Integer id, String label)
    {
        PaymentStatus paymentStatus = new PaymentStatus(id, label);
        int paymentStatusHash = paymentStatus.hashCode();

        if(paymentStatusMap.containsKey(paymentStatusHash))
        {
            return paymentStatusMap.get(paymentStatusHash);
        }
        else
        {
            paymentStatusMap.put(paymentStatusHash, paymentStatus);
            return paymentStatus;
        }
    }
}
