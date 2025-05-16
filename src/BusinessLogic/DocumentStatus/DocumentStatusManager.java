package BusinessLogic.DocumentStatus;

import DataAccess.DocumentStatus.DocumentStatusDBAccess;
import DataAccess.DocumentStatus.DocumentStatusDataAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DocumentStatus.GetAllDocumentStatusException;
import Exceptions.DocumentStatus.GetDocumentStatusException;
import Model.DocumentStatus.DocumentStatus;

import java.util.ArrayList;

public class DocumentStatusManager {
    private final DocumentStatusDBAccess documentStatusDBAccess = new DocumentStatusDBAccess();
    public DocumentStatusManager(){}

    public ArrayList<DocumentStatus> getAllDocumentStatus() throws GetAllDocumentStatusException {
        return documentStatusDBAccess.getAllDocumentStatus();
    }

    public void createDocumentStatus(DocumentStatus documentStatus) {
        documentStatusDBAccess.createDocumentStatus(documentStatus);
    }

    public void updateDocumentStatus(DocumentStatus documentStatus) {
        documentStatusDBAccess.updateDocumentStatus(documentStatus);
    }

    public void deleteDocumentStatus(Integer id) {
        documentStatusDBAccess.deleteDocumentStatus(id);
    }

    public DocumentStatus getDocumentStatus(Integer id) throws GetDocumentStatusException
    {
        return documentStatusDBAccess.getDocumentStatus(id);
    }

    public ArrayList<DocumentStatus> getAllDocumentStatusByDocumentId(Integer documentId) {
        return documentStatusDBAccess.getAllDocumentStatusByDocumentId(documentId);
    }


}
