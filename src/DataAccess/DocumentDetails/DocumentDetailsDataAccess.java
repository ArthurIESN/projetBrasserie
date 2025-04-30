package DataAccess.DocumentDetails;

import Model.Document.Document;
import Model.DocumentDetails.DocumentDetails;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface DocumentDetailsDataAccess
{
    void createDocumentDetails(DocumentDetails documentDetails);
    void updateDocumentDetails(DocumentDetails documentDetails);
    void deleteDocumentDetails(Integer id);
    DocumentDetails getDocumentDetails(Integer id);
    ArrayList<DocumentDetails> getAllDocumentDetails();

    ArrayList<DocumentDetails> getDocumentsDetailsFromDocuments(ArrayList<Document> documents);
}
