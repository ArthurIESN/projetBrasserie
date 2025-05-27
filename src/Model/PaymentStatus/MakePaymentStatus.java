package Model.PaymentStatus;

import Exceptions.PaymentStatus.PaymentStatusException;

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
            PaymentStatus paymentStatus;
            try {
                paymentStatus = new PaymentStatus(id, label);
                paymentStatusMap.put(paymentStatusHash, paymentStatus);
            } catch (PaymentStatusException e) {
                paymentStatus = null;
                System.err.println("Error creating PaymentStatus: " + e.getMessage());
            }

            return paymentStatus;
        }
    }
}
