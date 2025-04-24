package Controller.DocumentStatus;

import BusinessLogic.DocumentStatus.DocumentStatusManager;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Model.DocumentStatus.DocumentStatus;

import java.util.ArrayList;

public class DocumentStatusController {
    private static final DocumentStatusManager documentStatusManager = new DocumentStatusManager();

    public static ArrayList<DocumentStatus> getAllDocumentStatus() throws DatabaseConnectionFailedException,
            Exceptions.DocumentStatus.GetAllDocumentStatusException{
        return documentStatusManager.getAllDocumentStatus();
    }
}
