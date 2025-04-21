package Model.DocumentStatus;

import javax.swing.*;
import java.util.HashMap;

public class MakeDocumentStatus {
    private static final HashMap<Integer, DocumentStatus> documentStatusMap = new HashMap<>();

    public static DocumentStatus getDocumentStatus(Integer id, String label)
    {
        DocumentStatus documentStatus = new DocumentStatus(id, label);
        int documentStatusHash = documentStatus.hashCode();

        if(documentStatusMap.containsKey(documentStatusHash))
        {
            return documentStatusMap.get(documentStatusHash);
        }
        else
        {
            documentStatusMap.put(documentStatusHash,documentStatus);
            return documentStatus;
        }
    }
}
