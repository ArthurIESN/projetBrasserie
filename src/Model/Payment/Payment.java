package Model.Payment;
import Exceptions.Payment.PaymentException;
import Exceptions.PaymentStatus.PaymentStatusException;
import Model.PaymentStatus.PaymentStatus;
import Model.Customer.Customer;
import Model.Process.Process;
import Model.Document.Document;

import java.util.Date;

public class Payment {
    private Integer id;
    private double amount;
    private Date paymentDate;
    private PaymentStatus paymentStatus;
    private Document document;
    private Model.Process.Process process;
    private Customer customer;

    // Constructeur

    public Payment(Integer id, double amount, java.sql.Date paymentDate, PaymentStatus paymentStatus, Document document,
                   Model.Process.Process process, Customer customer) throws PaymentException
            {
        setId(id);
        setAmount(amount);
        setPaymentDate(paymentDate);
        setPaymentDate(paymentDate);
        setPaymentStatus(paymentStatus);
        setDocument(document);
        setProcess(process);
        setCustomer(customer);
    }

    // Getters et setters pour chaque champ

    public Integer getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Document getDocument() {
        return document;
    }

    public Process getProcess() {
        return process;
    }

    public Customer getCustomer() {
        return customer;
    }


    public void setId(Integer id) throws PaymentException {
        if(id == null || id <= 0){
            throw new IllegalArgumentException("ID cannot be null or less than or equal to 0");
        }
        this.id = id;
    }

    public void setAmount (double amount) throws PaymentException {
        if (amount < 0) {
            throw new PaymentException("Amount cannot be negative");
        }
        this.amount = amount;
    }

    public void setPaymentDate(Date paymentDate) throws PaymentException {
        if (paymentDate == null) {
            throw new PaymentException("Payment date cannot be null");
        }
        this.paymentDate = paymentDate;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) throws PaymentException{
        if (paymentStatus == null) {
            throw new PaymentException("Payment status cannot be null");
        }
        this.paymentStatus = paymentStatus;
    }

    public void setDocument(Document document) throws PaymentException{
        if(document == null) {
            throw new PaymentException("Document cannot be null");
        }
        this.document = document;
    }

    public void setProcess(Process process) throws PaymentException {
        if (process == null) {
            throw new PaymentException("Process cannot be null");
        }
        this.process = process;
    }

    public void setCustomer(Customer customer) throws PaymentException {
        if (customer == null) {
            throw new PaymentException("Customer cannot be null");
        }
        this.customer = customer;
    }

    public static int hashCode(int id, double amount, Date paymentDate, PaymentStatus paymentStatus, Document document, Model.Process.Process process, Customer customer) {
        return java.util.Objects.hash(id, amount, paymentDate, paymentStatus, document, process, customer);
    }
}


