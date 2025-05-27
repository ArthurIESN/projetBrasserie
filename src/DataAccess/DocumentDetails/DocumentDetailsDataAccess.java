package DataAccess.DocumentDetails;

import Exceptions.DocumentDetails.CreateDocumentDetailsException;
import Exceptions.DocumentDetails.GetDocumentDetailsFromDocumentsException;
import Exceptions.Search.GetQuantityItemWithSpecificEventException;
import Model.Document.Document;
import Model.DocumentDetails.DocumentDetails;

import java.util.ArrayList;

public interface DocumentDetailsDataAccess
{
    void createDocumentDetails(DocumentDetails documentDetails) throws CreateDocumentDetailsException;
    void updateDocumentDetails(DocumentDetails documentDetails);
    void deleteDocumentDetails(Integer id);
    DocumentDetails getDocumentDetails(Integer id);
    ArrayList<DocumentDetails> getAllDocumentDetails();

    ArrayList<Integer> getQuantityItemWithSpecificEvent(int idEvent, int idItem) throws GetQuantityItemWithSpecificEventException;
}
