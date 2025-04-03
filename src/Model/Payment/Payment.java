package Model.Payment;
import Model.Customer.Customer;
import Model.PaymentStatus.PaymentStatus;
import Model.Document.Document;

import java.util.Date;

public class Payment {
    private int id;
    private double amount;
    private java.sql.Date paymentDate;
    private PaymentStatus paymentStatus;
    private Document document;
    private Process process;
    private Customer customer;

    // Constructeur
    public Payment(int id, double amount, java.sql.Date paymentDate, PaymentStatus paymentStatus) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    // Getters et setters pour chaque champ

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(java.sql.Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}


