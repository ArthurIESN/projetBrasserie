package Model.DocumentStatus;

import javax.swing.*;
import java.util.HashMap;

public class MakeDocumentStatus {
    private static final HashMap<Integer, DocumentStatus> documentStatusMap = new HashMap<>();

    public static DocumentStatus getDocumentStatus(Integer id, String label){
        if(documentStatusMap.containsKey(id)){
            return documentStatusMap.get(id);
        }else{
            DocumentStatus documentStatus = new DocumentStatus(id,label);
            documentStatusMap.put(id,documentStatus);
            return documentStatus;
        }
    }
}
