package BusinessLogic.DocumentStatus;

import DataAccess.DocumentStatus.DocumentStatusDBAccess;
import DataAccess.DocumentStatus.DocumentStatusDataAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Model.DocumentStatus.DocumentStatus;

import java.util.ArrayList;

public class DocumentStatusManager {
    private DocumentStatusDBAccess documentStatusDBAccess;
    public DocumentStatusManager(){documentStatusDBAccess = new DocumentStatusDBAccess();}

    public ArrayList<DocumentStatus> getAllDocumentStatus() throws DatabaseConnectionFailedException {
        return documentStatusDBAccess.getAllDocumentStatus();
    }
}
