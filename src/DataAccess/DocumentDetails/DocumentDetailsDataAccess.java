package DataAccess.DocumentDetails;

import Model.DocumentDetails.DocumentDetails;

import java.util.ArrayList;

public interface DocumentDetailsDataAccess
{
    void createDocumentDetails(DocumentDetails documentDetails);
    void updateDocumentDetails(DocumentDetails documentDetails);
    void deleteDocumentDetails(Integer id);
    DocumentDetails getDocumentDetails(Integer id);
    ArrayList<DocumentDetails> getAllDocumentDetails();
}
