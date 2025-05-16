package Model.Document;

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
    private Float VatAmount;
    private Float totalInclusiveOfTaxe;
    private Float totalVat;
    private Float totalExclVat;
    private CollectionAgency collectionAgency;
    private DeliveryTruck deliveryTruck;
    private Process process;
    private DocumentStatus documentStatus;

    // Commande client
    public Document(Integer id, String label, Date date, Date deadLine, Float reduction, String validity,
                    Boolean isDelivered, Date deliveryDate, Boolean depositIsPaid, Float depositAmount,
                    Date desiredDeliveryDate, Float VatAmount, Float totalInclusiveOfTaxe, Float totalVat,
                    Float totalExclVat, CollectionAgency collectionAgency, DeliveryTruck deliveryTruck,
                    Process process,DocumentStatus documentStatus)
    {
        setId(id);
        this.label = label;
        this.date = date;
        this.deadLine = deadLine;
        this.reduction = reduction;
        this.validity = validity;
        this.isDelivered = isDelivered;
        this.deliveryDate = deliveryDate;
        this.depositIsPaid = depositIsPaid;
        this.depositAmount = depositAmount;
        this.desiredDeliveryDate = desiredDeliveryDate;
        this.VatAmount = VatAmount;
        this.totalInclusiveOfTaxe = totalInclusiveOfTaxe;
        this.totalVat = totalVat;
        this.totalExclVat = totalExclVat;
        this.collectionAgency = collectionAgency;
        this.deliveryTruck = deliveryTruck;
        this.process = process;
        this.documentStatus = documentStatus;
    }

    private void setId(Integer id)
    {
        if(id == null || id <= 0)
        {
            id = null;
        }
        else
        {
            this.id = id;
        }
    }


    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Date getDate() {
        return date;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public Float getReduction() {
        return reduction;
    }

    public String getValidity() {
        return validity;
    }

    public Boolean getIsDelivered() {
        return isDelivered;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public Boolean getDepositIsPaid() {
        return depositIsPaid;
    }

    public Float getDepositAmount() {
        return depositAmount;
    }

    public Date getDesiredDeliveryDate() {
        return desiredDeliveryDate;
    }

    public Float getVatAmount() {
        return VatAmount;
    }

    public Float getTotalInclusiveOfTaxe() {
        return totalInclusiveOfTaxe;
    }

    public Float getTotalVat() {
        return totalVat;
    }

    public Float getTotalExclVat() {
        return totalExclVat;
    }

    public CollectionAgency getCollectionAgency() {
        return collectionAgency;
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

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public void setReduction(Float reduction) {
        this.reduction = reduction;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public void setIsDelivered(Boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setDepositIsPaid(Boolean depositIsPaid) {
        this.depositIsPaid = depositIsPaid;
    }

    public void setDepositAmount(Float depositAmount) {
        this.depositAmount = depositAmount;
    }

    public void setDesiredDeliveryDate(Date desiredDeliveryDate) {
        this.desiredDeliveryDate = desiredDeliveryDate;
    }

    public void setVatAmount(Float vatAmount) {
        this.VatAmount = vatAmount;
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
                Objects.equals(VatAmount, document.VatAmount) &&
                Objects.equals(totalInclusiveOfTaxe, document.totalInclusiveOfTaxe) &&
                Objects.equals(totalVat, document.totalVat) &&
                Objects.equals(totalExclVat, document.totalExclVat) &&
                Objects.equals(collectionAgency, document.collectionAgency) &&
                Objects.equals(deliveryTruck, document.deliveryTruck) &&
                Objects.equals(process, document.process);
    }

    public static int hashCode(Integer id, String label, Date date, Date deadLine, Float reduction,
                               String validity, Boolean isDelivered, Date deliveryDate, Boolean depositIsPaid,
                               Float depositAmount, Date desiredDeliveryDate, Float VatAmount,
                               Float totalInclusiveOfTaxe, Float totalVat, Float totalExclVat,
                               CollectionAgency collectionAgency, DeliveryTruck deliveryTruck,
                               Process process, DocumentStatus documentStatus)
    {
        return Objects.hash(id, label, date, deadLine, reduction, validity, isDelivered, deliveryDate,
                depositIsPaid, depositAmount, desiredDeliveryDate, VatAmount, totalInclusiveOfTaxe,
                totalVat, totalExclVat, collectionAgency, deliveryTruck, process, documentStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, date, deadLine, reduction, validity, isDelivered, deliveryDate,
                depositIsPaid, depositAmount, desiredDeliveryDate, VatAmount, totalInclusiveOfTaxe,
                totalVat, totalExclVat, collectionAgency, deliveryTruck, process);
    }

    @Override
    public String toString() {
        return id + " - " + label + " - " + date + " - " + deadLine + " - " + reduction + " - " + validity + " - " + isDelivered + " - " + deliveryDate + " - " + depositIsPaid + " - " + depositAmount + " - " + desiredDeliveryDate + " - " + VatAmount + " - " + totalInclusiveOfTaxe + " - " + totalVat + " - " + totalExclVat + " - " + collectionAgency + " - " + deliveryTruck + " - " + process + " - " + documentStatus;
    }


}
