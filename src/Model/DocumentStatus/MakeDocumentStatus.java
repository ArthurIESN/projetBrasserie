package Model.DocumentStatus;

import Exceptions.DocumentStatus.DocumentStatusException;

import javax.swing.*;
import java.util.HashMap;

public class MakeDocumentStatus {
    private static final HashMap<Integer, DocumentStatus> documentStatusMap = new HashMap<>();

    public static DocumentStatus getDocumentStatus(Integer id, String label)
    {
        int documentStatusHash = DocumentStatus.hashCode(id, label);

        if(documentStatusMap.containsKey(documentStatusHash))
        {
            return documentStatusMap.get(documentStatusHash);
        }
        else
        {
            try
            {
                DocumentStatus documentStatus = new DocumentStatus(id, label);
                documentStatusMap.put(documentStatusHash,documentStatus);
                return documentStatus;
            }
            catch (DocumentStatusException e)
            {
                System.out.println(e.getMessage());
                return null;
            }

        }
    }
}
