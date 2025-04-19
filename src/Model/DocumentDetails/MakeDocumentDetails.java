package Model.DocumentDetails;

import Model.Document.Document;

import java.util.HashMap;

public class MakeDocumentDetails {
    private static final HashMap<Integer,DocumentDetails> documentDetailsMap = new HashMap<>();

    public static DocumentDetails getDocumentDetails(Integer id, String label, Float quantity, Float newQuantity, Float unitPrice, Document document)
    {
        DocumentDetails documentDetails = new DocumentDetails(id, label, quantity, newQuantity, unitPrice, document);
        int documentDetailsHash = documentDetails.hashCode();

        if(documentDetailsMap.containsKey(documentDetailsHash))
        {
            return documentDetailsMap.get(documentDetailsHash);
        }
        else
        {
            documentDetailsMap.put(documentDetailsHash,documentDetails);
            return documentDetails;
        }
    }
}
