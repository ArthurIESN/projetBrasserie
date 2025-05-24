package BusinessLogic.DocumentDetails;

import DataAccess.DocumentDetails.DocumentDetailsDBAccess;
import DataAccess.DocumentDetails.DocumentDetailsDataAccess;
import Exceptions.DocumentDetails.CreateDocumentDetailsException;
import Exceptions.DocumentDetails.GetDocumentDetailsFromDocumentsException;
import Exceptions.Search.GetQuantityItemWithSpecificEventException;
import Model.Document.Document;
import Model.DocumentDetails.DocumentDetails;

import java.util.ArrayList;

public class DocumentDetailsManager
{
    private static final DocumentDetailsDataAccess documentDetailsDataAccess = new DocumentDetailsDBAccess();

    public void createDocumentDetails(DocumentDetails documentDetails) throws CreateDocumentDetailsException
    {
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

    public ArrayList<DocumentDetails> getDocumentsDetailsFromDocuments(ArrayList<Document> documents) throws GetDocumentDetailsFromDocumentsException
    {
        return documentDetailsDataAccess.getDocumentsDetailsFromDocuments(documents);
    }

    public ArrayList<Integer> getQuantityItemWithSpecificEvent(int idEvent, int idItem) throws GetQuantityItemWithSpecificEventException
    {
        return documentDetailsDataAccess.getQuantityItemWithSpecificEvent(idEvent, idItem);
    }
}
