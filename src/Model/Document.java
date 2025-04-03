package Model;

import Model.DeliveryTruck.DeliveryTruck;
import Model.DocumentStatus.DocumentStatus;
import Model.Process.Process;

import java.util.Date;

public class Document {
    private Integer id;
    private String label;
    private Date date;
    private Date deadLine;
    private float reduction;
    private String validity;
    private Boolean isDelivered;
    private Date deliveryDate;
    private Boolean depositIsPaid;
    private float depositAmount;
    private Date desiredDeliveryDate;
    private float VatAmount;
    private float totalInclusiveOfTaxe;
    private float totalVat;
    private float totalExclVat;
    private CollectionAgency collectionAgency;
    private DeliveryTruck deliveryTruck;
    private Process process;
    private DocumentStatus documentStatus;

    // Commande client
    public Document(Integer id, String label, Date date, Date deadLine, float reduction, String validity,
                    Boolean isDelivered, Date deliveryDate, Boolean depositIsPaid, float depositAmount,
                    Date desiredDeliveryDate, float VatAmount, float totalInclusiveOfTaxe, float totalVat,
                    float totalExclVat, CollectionAgency collectionAgency, DeliveryTruck deliveryTruck,
                    Process process, DocumentStatus documentStatus)
    {
        this.id = id;
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

    // Commande fournisseur


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

    public float getReduction() {
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

    public float getDepositAmount() {
        return depositAmount;
    }

    public Date getDesiredDeliveryDate() {
        return desiredDeliveryDate;
    }

    public float getVatAmount() {
        return VatAmount;
    }

    public float getTotalInclusiveOfTaxe() {
        return totalInclusiveOfTaxe;
    }

    public float getTotalVat() {
        return totalVat;
    }

    public float getTotalExclVat() {
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

    @Override
    public String toString() {
        return id + " - " + label + " - " + date + " - " + deadLine + " - " + reduction + " - " + validity + " - " + isDelivered + " - " + deliveryDate + " - " + depositIsPaid + " - " + depositAmount + " - " + desiredDeliveryDate + " - " + VatAmount + " - " + totalInclusiveOfTaxe + " - " + totalVat + " - " + totalExclVat + " - " + collectionAgency + " - " + deliveryTruck + " - " + process + " - " + documentStatus;
    }


}
