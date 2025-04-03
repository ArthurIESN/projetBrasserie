package Model.DocumentDetails;

import java.util.HashMap;

public class MakeDocumentDetails {
    private static final HashMap<Integer,DocumentDetails> documentDetailsMap = new HashMap<>();

    public static DocumentDetails getDocumentDetails(Integer id, String label, Float quantity, Float new_quantity, Float unit_price, Document document){
        if(documentDetailsMap.containsKey(id)){
            return documentDetailsMap.get(id);
        }else{
            DocumentDetails documentDetails = new DocumentDetails(id,label,quantity,new_quantity,unit_price,document);
            documentDetailsMap.put(id,documentDetails);
            return documentDetails;
        }
    }
}
