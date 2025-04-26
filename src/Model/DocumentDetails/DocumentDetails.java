package Model.DocumentDetails;

import Model.Document.Document;

import java.util.Objects;

public class DocumentDetails {
    private Integer id;
    private String label;
    private float quantity;
    private float newQuantity;
    private float unitPrice;
    private Document document;

    public DocumentDetails(Integer id, String label, float quantity, float newQuantity,
                           float unitPrice, Document document)
    {
        this.id = id;
        this.label = label;
        this.quantity = quantity;
        this.newQuantity = newQuantity;
        this.unitPrice = unitPrice;
        this.document = document;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public float getQuantity() {
        return quantity;
    }

    public float getNewQuantity() {
        return newQuantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public Document getDocument() {
        return document;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        DocumentDetails that = (DocumentDetails) obj;

        return Objects.equals(id, that.id) &&
                Objects.equals(label, that.label) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(newQuantity, that.newQuantity) &&
                Objects.equals(unitPrice, that.unitPrice) &&
                Objects.equals(document, that.document);
    }

    public static int hashCode(Integer id, String label, float quantity, float newQuantity,
                                float unitPrice, Document document)
    {
        return Objects.hash(id, label, quantity, newQuantity, unitPrice, document);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, label, quantity, newQuantity, unitPrice, document);
    }

    @Override
    public String toString() {
        return id + " - " + label + " - " + quantity + " - " + newQuantity + " - " + unitPrice + " - " + document;
    }
}
