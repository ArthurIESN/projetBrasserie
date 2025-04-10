package Model.Payment;
import Model.PaymentStatus.PaymentStatus;
import Model.Customer.Customer;
import Model.Process.Process;
import Model.Document.Document;

import java.util.Date;
import java.util.Objects;

public class Payment {
    private int id;
    private double amount;
    private Date paymentDate;
    private PaymentStatus paymentStatus;
    private Document document;
    private Model.Process.Process process;
    private Customer customer;

    // Constructeur

    public Payment(int id, double amount, java.sql.Date paymentDate, PaymentStatus paymentStatus, Document document,Model.Process.Process process, Customer customer) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.document = document;
        this.process = process;
        this.customer = customer;
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

    public void setProcess(Model.Process.Process process) {
        this.process = process;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Payment payment = (Payment) obj;

        return Objects.equals(id, payment.id) &&
                Objects.equals(amount, payment.amount) &&
                Objects.equals(paymentDate, payment.paymentDate) &&
                Objects.equals(paymentStatus, payment.paymentStatus) &&
                Objects.equals(document, payment.document) &&
                Objects.equals(process, payment.process) &&
                Objects.equals(customer, payment.customer);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, amount, paymentDate, paymentStatus, document, process, customer);
    }
}


