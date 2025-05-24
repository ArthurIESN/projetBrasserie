package DataAccess.DocumentStatus;

import Exceptions.DocumentStatus.GetAllDocumentStatusException;
import Exceptions.DocumentStatus.GetDocumentStatusException;
import Model.DocumentStatus.DocumentStatus;

import java.util.ArrayList;

public interface DocumentStatusDataAccess {
    ArrayList<DocumentStatus> getAllDocumentStatus() throws GetAllDocumentStatusException;

    void createDocumentStatus(DocumentStatus documentStatus);
    void updateDocumentStatus(DocumentStatus documentStatus);
    void deleteDocumentStatus(Integer id);
    DocumentStatus getDocumentStatus(Integer id) throws GetDocumentStatusException;
    ArrayList<DocumentStatus> getAllDocumentStatusByDocumentId(Integer documentId);
}
