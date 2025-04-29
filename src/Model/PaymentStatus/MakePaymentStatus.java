package Model.PaymentStatus;

import java.util.HashMap;

public class MakePaymentStatus {
    private static final HashMap<Integer, PaymentStatus> paymentStatusMap = new HashMap<>();

    public static PaymentStatus getPaymentStatus(Integer id, String label)
    {
        int paymentStatusHash = PaymentStatus.hashCode(id, label);

        if(paymentStatusMap.containsKey(paymentStatusHash))
        {
            return paymentStatusMap.get(paymentStatusHash);
        }
        else
        {
            PaymentStatus paymentStatus = new PaymentStatus(id, label);
            paymentStatusMap.put(paymentStatusHash, paymentStatus);
            return paymentStatus;
        }
    }
}
