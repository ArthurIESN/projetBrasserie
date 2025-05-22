package Model;

import Model.DocumentDetails.DocumentDetails;
import Model.Item.Item;

import java.util.Objects;

public class ItemDocumentDetails {
    private Integer id;
    private DocumentDetails documentDetails;
    private Item item;

    public ItemDocumentDetails(Integer id, DocumentDetails documentDetails, Item item) {
        this.id = id;
        this.documentDetails = documentDetails;
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public DocumentDetails getDocumentDetails() {
        return documentDetails;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ItemDocumentDetails that = (ItemDocumentDetails) obj;

        return Objects.equals(id, that.id) &&
                Objects.equals(documentDetails, that.documentDetails) &&
                Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentDetails, item);
    }

    @Override
    public String toString() {
        return id + " - " + documentDetails + " - " + item;
    }
}