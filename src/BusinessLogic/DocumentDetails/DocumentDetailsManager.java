package BusinessLogic.DocumentDetails;

import DataAccess.DocumentDetails.DocumentDetailsDBAccess;
import DataAccess.DocumentDetails.DocumentDetailsDataAccess;
import Model.DocumentDetails.DocumentDetails;

import java.util.ArrayList;

public class DocumentDetailsManager
{
    private static final DocumentDetailsDataAccess documentDetailsDataAccess = new DocumentDetailsDBAccess();

    public void createDocumentDetails(DocumentDetails documentDetails) {
        documentDetailsDataAccess.createDocumentDetails(documentDetails);
    }

    public void updateDocumentDetails(DocumentDetails documentDetails) {
        documentDetailsDataAccess.updateDocumentDetails(documentDetails);
    }

    public void deleteDocumentDetails(Integer id) {
        documentDetailsDataAccess.deleteDocumentDetails(id);
    }

    public DocumentDetails getDocumentDetails(Integer id) {
        return documentDetailsDataAccess.getDocumentDetails(id);
    }

    public ArrayList<DocumentDetails> getAllDocumentDetails() {
        return documentDetailsDataAccess.getAllDocumentDetails();
    }
}
