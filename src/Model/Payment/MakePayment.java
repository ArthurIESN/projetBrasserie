package Model.Payment;

import Exceptions.Payment.PaymentException;
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
        int paymentHash = Payment.hashCode(id, amount, paymentDate, paymentStatus, document, process, customer);

        if(paymentMap.containsKey(paymentHash))
        {
            return paymentMap.get(paymentHash);
        }
        else
        {
            Payment payment;
            try
            {
                payment = new Payment(id, amount, paymentDate, paymentStatus, document, process, customer);
                paymentMap.put(paymentHash, payment);
            }
            catch (PaymentException e)
            {
                payment = null;
                System.err.println("Error creating Payment: " + e.getMessage());
            }

            return payment;
        }
    }
}
