package Model.DocumentDetails;

import Model.Document.Document;
import Model.Item.Item;

import java.util.HashMap;

public class MakeDocumentDetails {
    private static final HashMap<Integer,DocumentDetails> documentDetailsMap = new HashMap<>();

    public static DocumentDetails getDocumentDetails(Integer id, String label, Integer quantity, Integer newQuantity, Float unitPrice, Document document, Item item)
    {
        int documentDetailsHash = DocumentDetails.hashCode(id, label, quantity, newQuantity, unitPrice, document, item);

        if(documentDetailsMap.containsKey(documentDetailsHash))
        {
            return documentDetailsMap.get(documentDetailsHash);
        }
        else
        {
            DocumentDetails documentDetails = new DocumentDetails(id, label, quantity, newQuantity, unitPrice, document, item);
            documentDetailsMap.put(documentDetailsHash,documentDetails);
            return documentDetails;
        }
    }
}
