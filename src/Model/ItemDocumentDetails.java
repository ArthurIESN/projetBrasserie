package Model;

import Model.DocumentDetails.DocumentDetails;
import Model.Item.Item;

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
    public String toString() {
        return id + " - " + documentDetails + " - " + item;
    }
}
