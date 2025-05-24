package Controller.DocumentDetails;

import BusinessLogic.DocumentDetails.DocumentDetailsManager;
import Exceptions.DocumentDetails.CreateDocumentDetailsException;
import Exceptions.DocumentDetails.GetDocumentDetailsFromDocumentsException;
import Exceptions.Search.GetQuantityItemWithSpecificEventException;
import Model.Document.Document;
import Model.DocumentDetails.DocumentDetails;

import java.util.ArrayList;

public class DocumentDetailsController
{
    private static final DocumentDetailsManager documentDetailsManager = new DocumentDetailsManager();

    public static void createDocumentDetails(DocumentDetails documentDetails) throws CreateDocumentDetailsException
    {
        documentDetailsManager.createDocumentDetails(documentDetails);
    }

    public static void updateDocumentDetails(DocumentDetails documentDetails)
    {
        documentDetailsManager.updateDocumentDetails(documentDetails);
    }

    public static void deleteDocumentDetails(Integer id)
    {
        documentDetailsManager.deleteDocumentDetails(id);
    }

    public static DocumentDetails getDocumentDetails(Integer id)
    {
        return documentDetailsManager.getDocumentDetails(id);
    }

    public static ArrayList<DocumentDetails> getAllDocumentDetails()
    {
        return documentDetailsManager.getAllDocumentDetails();
    }

    public static ArrayList<DocumentDetails> getDocumentsDetailsFromDocuments(ArrayList<Document> documents) throws GetDocumentDetailsFromDocumentsException
    {
        return documentDetailsManager.getDocumentsDetailsFromDocuments(documents);
    }

    public static ArrayList<Integer> getQuantityItemWithSpecificEvent(int idEvent, int idItem) throws GetQuantityItemWithSpecificEventException
    {
        return documentDetailsManager.getQuantityItemWithSpecificEvent(idEvent, idItem);
    }

}
