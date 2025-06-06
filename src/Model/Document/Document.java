package Model.Document;

import Exceptions.Document.DocumentException;
import Model.CollectionAgency.CollectionAgency;
import Model.DeliveryTruck.DeliveryTruck;
import Model.DocumentStatus.DocumentStatus;
import Model.Process.Process;

import java.util.Date;
import java.util.Objects;

public class Document {
    private Integer id;
    private String label;
    private Date date;
    private Date deadLine;
    private Float reduction;
    private String validity;
    private Boolean isDelivered;
    private Date deliveryDate;
    private Boolean depositIsPaid;
    private Float depositAmount;
    private Date desiredDeliveryDate;
    private Float totalInclusiveOfTax;
    private Float totalVat;
    private Float totalExclVat;
    private CollectionAgency collectionAgency;
    private DeliveryTruck deliveryTruck;
    private Process process;
    private DocumentStatus documentStatus;

    public Document(Integer id, String label, Date date, Date deadLine, Float reduction, String validity,
                    Boolean isDelivered, Date deliveryDate, Boolean depositIsPaid, Float depositAmount,
                    Date desiredDeliveryDate, Float totalInclusiveOfTax, Float totalVat,
                    Float totalExclVat, CollectionAgency collectionAgency, DeliveryTruck deliveryTruck,
                    Process process, DocumentStatus documentStatus) throws DocumentException
    {
        setId(id);
        setLabel(label);
        setDate(date);
        setDeadLine(deadLine);
        setReduction(reduction);
        setValidity(validity);
        setIsDelivered(isDelivered);
        setDeliveryDate(deliveryDate);
        setDepositIsPaid(depositIsPaid);
        setDepositAmount(depositAmount);
        setDesiredDeliveryDate(desiredDeliveryDate);
        setTotalInclusiveOfTax(totalInclusiveOfTax);
        setTotalVat(totalVat);
        setTotalExclVat(totalExclVat);
        setCollectionAgency(collectionAgency);
        setDeliveryTruck(deliveryTruck);
        setProcess(process);
        setDocumentStatus(documentStatus);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) throws DocumentException
    {
        if(id == null || id <= 0)
        {
            throw new DocumentException("ID cannot be null or less than 1");
        }
        else
        {
            this.id = id;
        }
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) throws DocumentException
    {
        if(label == null || label.isEmpty())
        {
            throw new DocumentException("Label cannot be null or empty");
        }
        else
        {
            this.label = label;
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) throws DocumentException
    {
        if(date == null)
        {
            throw new DocumentException("Date cannot be null");
        }
        else
        {
            this.date = date;
        }
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine)
    {
        this.deadLine = deadLine;
    }

    public Float getReduction() {
        return reduction;
    }

    public void setReduction(Float reduction)
    {
        this.reduction = reduction;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity)
    {
        this.validity = validity;
    }

    public Boolean getIsDelivered() {
        return isDelivered;
    }

    public void setIsDelivered(Boolean isDelivered)
    {
        this.isDelivered = isDelivered;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate)
    {
        this.deliveryDate = deliveryDate;
    }

    public Boolean getDepositIsPaid() {
        return depositIsPaid;
    }

    public void setDepositIsPaid(Boolean depositIsPaid)
    {
        this.depositIsPaid = depositIsPaid;
    }

    public Float getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Float depositAmount)
    {
        this.depositAmount = depositAmount;
    }

    public Date getDesiredDeliveryDate() {
        return desiredDeliveryDate;
    }

    public void setDesiredDeliveryDate(Date desiredDeliveryDate) {
        this.desiredDeliveryDate = desiredDeliveryDate;
    }


    public Float getTotalInclusiveOfTax() {
        return totalInclusiveOfTax;
    }

    public void setTotalInclusiveOfTax(Float totalInclusiveOfTax) {
        this.totalInclusiveOfTax = totalInclusiveOfTax;
    }

    public Float getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(Float totalVat) {
        this.totalVat = totalVat;
    }


    public Float getTotalExclVat() {
        return totalExclVat;
    }

    public void setTotalExclVat(Float totalExclVat) {
        this.totalExclVat = totalExclVat;
    }

    public CollectionAgency getCollectionAgency() {
        return collectionAgency;
    }

    public void setCollectionAgency(CollectionAgency collectionAgency) {
        this.collectionAgency = collectionAgency;
    }

    public DeliveryTruck getDeliveryTruck() {
        return deliveryTruck;
    }

    public Process getProcess() {
        return process;
    }

    public DocumentStatus getDocumentStatus(){return documentStatus;}

    public void setProcess(Process process) {
        this.process = process;
    }

    public void setDocumentStatus(DocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
    }

    public void setDeliveryTruck(DeliveryTruck deliveryTruck) {
        this.deliveryTruck = deliveryTruck;
    }





    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Document document = (Document) obj;

        return Objects.equals(id, document.id) &&
                Objects.equals(label, document.label) &&
                Objects.equals(date, document.date) &&
                Objects.equals(deadLine, document.deadLine) &&
                Objects.equals(reduction, document.reduction) &&
                Objects.equals(validity, document.validity) &&
                Objects.equals(isDelivered, document.isDelivered) &&
                Objects.equals(deliveryDate, document.deliveryDate) &&
                Objects.equals(depositIsPaid, document.depositIsPaid) &&
                Objects.equals(depositAmount, document.depositAmount) &&
                Objects.equals(desiredDeliveryDate, document.desiredDeliveryDate) &&
                Objects.equals(totalInclusiveOfTax, document.totalInclusiveOfTax) &&
                Objects.equals(totalVat, document.totalVat) &&
                Objects.equals(totalExclVat, document.totalExclVat) &&
                Objects.equals(collectionAgency, document.collectionAgency) &&
                Objects.equals(deliveryTruck, document.deliveryTruck) &&
                Objects.equals(process, document.process);
    }

    public static int hashCode(Integer id, String label, Date date, Date deadLine, Float reduction,
                               String validity, Boolean isDelivered, Date deliveryDate, Boolean depositIsPaid,
                               Float depositAmount, Date desiredDeliveryDate,
                               Float totalInclusiveOfTax, Float totalVat, Float totalExclVat,
                               CollectionAgency collectionAgency, DeliveryTruck deliveryTruck,
                               Process process, DocumentStatus documentStatus)
    {
        return Objects.hash(id, label, date, deadLine, reduction, validity, isDelivered, deliveryDate,
                depositIsPaid, depositAmount, desiredDeliveryDate, totalInclusiveOfTax,
                totalVat, totalExclVat, collectionAgency, deliveryTruck, process, documentStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, date, deadLine, reduction, validity, isDelivered, deliveryDate,
                depositIsPaid, depositAmount, desiredDeliveryDate, totalInclusiveOfTax,
                totalVat, totalExclVat, collectionAgency, deliveryTruck, process);
    }

    @Override
    public String toString() {
        return id + " - " + label + " - " + date + " - " + deadLine + " - " + reduction + " - " + validity + " - " + isDelivered + " - " + deliveryDate + " - " + depositIsPaid + " - " + depositAmount + " - " + desiredDeliveryDate  + " - " + totalInclusiveOfTax + " - " + totalVat + " - " + totalExclVat + " - " + collectionAgency + " - " + deliveryTruck + " - " + process + " - " + documentStatus;
    }


}
