package Controller.DocumentStatus;

import BusinessLogic.DocumentStatus.DocumentStatusManager;
import Exceptions.DocumentStatus.GetAllDocumentStatusException;
import Exceptions.DocumentStatus.GetDocumentStatusException;
import Model.DocumentStatus.DocumentStatus;

import java.util.ArrayList;

public class DocumentStatusController {
    private static final DocumentStatusManager documentStatusManager = new DocumentStatusManager();

    public static ArrayList<DocumentStatus> getAllDocumentStatus() throws GetAllDocumentStatusException
    {
        return documentStatusManager.getAllDocumentStatus();
    }

    public static void createDocumentStatus(DocumentStatus documentStatus)
    {
        documentStatusManager.createDocumentStatus(documentStatus);
    }

    public static void updateDocumentStatus(DocumentStatus documentStatus)
    {
        documentStatusManager.updateDocumentStatus(documentStatus);
    }

    public static void deleteDocumentStatus(Integer id)
    {
        documentStatusManager.deleteDocumentStatus(id);
    }

    public static DocumentStatus getDocumentStatus(Integer id) throws GetDocumentStatusException
    {
        return documentStatusManager.getDocumentStatus(id);
    }

    public static ArrayList<DocumentStatus> getAllDocumentStatusByDocumentId(Integer documentId)
    {
        return documentStatusManager.getAllDocumentStatusByDocumentId(documentId);
    }
}
