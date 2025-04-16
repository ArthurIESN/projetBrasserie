package DataAccess.DocumentStatus;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Model.DocumentStatus.DocumentStatus;

import java.util.ArrayList;

public interface DocumentStatusDataAccess {
    public ArrayList<DocumentStatus> getAllDocumentStatus() throws DatabaseConnectionFailedException;
}
