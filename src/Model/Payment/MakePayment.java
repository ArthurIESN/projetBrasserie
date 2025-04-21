package Model.Payment;

import Model.Document.Document;
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
        if(paymentMap.containsKey(id))
        {
            return paymentMap.get(id);
        }
        else
        {
            Payment payment = new Payment(id, amount, paymentDate, paymentStatus, document, process, customer);
            paymentMap.put(id, payment);
            return payment;
        }
    }
}
