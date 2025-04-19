package Model.Payment;

import Model.PaymentStatus.PaymentStatus;
import Model.Process.Process;
import Model.Customer.Customer;
import Model.Document.Document;
import Model.ProcessType.ProcessType;

import java.sql.Date;
import java.util.HashMap;

public class MakePayment {
    private static final HashMap<Integer, Payment> paymentMap = new HashMap<>();

    public static Payment getPayment(Integer id, double amount, Date paymentDate, PaymentStatus paymentStatus, Document document, Process process, Customer customer)
    {
        Payment payment = new Payment(id, amount, paymentDate, paymentStatus, document, process, customer);
        int paymentHash = payment.hashCode();

        if(paymentMap.containsKey(paymentHash))
        {
            return paymentMap.get(paymentHash);
        }
        else
        {
            paymentMap.put(paymentHash, payment);
            return payment;
        }
    }
}
