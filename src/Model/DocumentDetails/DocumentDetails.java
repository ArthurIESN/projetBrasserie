package Model.DocumentDetails;

import Model.Document.Document;
import Model.Item.Item;

import java.util.Objects;

public class DocumentDetails {
    private Integer id;
    private String label;
    private Integer quantity;
    private Integer newQuantity;
    private float unitPrice;
    private Document document;
    private Item item;

    public DocumentDetails(Integer id, String label, int quantity, Integer newQuantity,
                           float unitPrice, Document document, Item item)
    {
        this.id = id;
        this.label = label;
        this.quantity = quantity;
        this.newQuantity = newQuantity;
        this.unitPrice = unitPrice;
        this.document = document;
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getNewQuantity() {
        return newQuantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public Document getDocument() {
        return document;
    }

    public Item getItem() {
        return item;
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
                Objects.equals(document, that.document) &&
                Objects.equals(item, that.item);
    }

    public static int hashCode(Integer id, String label, float quantity, float newQuantity,
                                float unitPrice, Document document, Item item)
    {
        return Objects.hash(id, label, quantity, newQuantity, unitPrice, document, item);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, label, quantity, newQuantity, unitPrice, document, item);
    }

    @Override
    public String toString() {
        return id + " - " + label + " - " + quantity + " - " + newQuantity + " - " + unitPrice + " - " + document + " - " + item;
    }
}
