package Model.DocumentDetails;

import Exceptions.DocumentDetails.DocumentDetailsException;
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
                           float unitPrice, Document document, Item item) throws DocumentDetailsException
    {
        setId(id);
        setLabel(label);
        setQuantity(quantity);
        this.newQuantity = newQuantity;
        setUnitPrice(unitPrice);
        setDocument(document);
        setItem(item);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) throws DocumentDetailsException
    {
        if(id == null || id <= 0)
        {
            throw new DocumentDetailsException("ID cannot be null or less than or equal to 0");
        }
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) throws DocumentDetailsException
    {
        if(label == null || label.isEmpty())
        {
            throw new DocumentDetailsException("Label cannot be null or empty");
        }
        this.label = label;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) throws DocumentDetailsException
    {
        if(quantity == null || quantity <= 0)
        {
            throw new DocumentDetailsException("Quantity cannot be null or less than or equal to 0");
        }
        this.quantity = quantity;
    }

    public Integer getNewQuantity() {
        return newQuantity;
    }


    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) throws DocumentDetailsException
    {
        if(unitPrice < 0)
        {
            throw new DocumentDetailsException("Unit price cannot be negative");
        }
        this.unitPrice = unitPrice;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) throws DocumentDetailsException
    {
        if(document == null)
        {
            throw new DocumentDetailsException("Document cannot be null");
        }
        this.document = document;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) throws DocumentDetailsException
    {
        if(item == null)
        {
            throw new DocumentDetailsException("Item cannot be null");
        }
        this.item = item;
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
