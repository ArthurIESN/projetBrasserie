package Model.DocumentDetails;

import Model.Document.Document;

import java.util.HashMap;

public class MakeDocumentDetails {
    private static final HashMap<Integer,DocumentDetails> documentDetailsMap = new HashMap<>();

    public static DocumentDetails getDocumentDetails(Integer id, String label, Integer quantity, Integer newQuantity, Float unitPrice, Document document)
    {
        int documentDetailsHash = DocumentDetails.hashCode(id, label, quantity, newQuantity, unitPrice, document);

        if(documentDetailsMap.containsKey(documentDetailsHash))
        {
            return documentDetailsMap.get(documentDetailsHash);
        }
        else
        {
            DocumentDetails documentDetails = new DocumentDetails(id, label, quantity, newQuantity, unitPrice, document);
            documentDetailsMap.put(documentDetailsHash,documentDetails);
            return documentDetails;
        }
    }
}
