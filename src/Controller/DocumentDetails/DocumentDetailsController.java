package Controller.DocumentDetails;

import BusinessLogic.DocumentDetails.DocumentDetailsManager;
import Model.DocumentDetails.DocumentDetails;

import java.util.ArrayList;

public class DocumentDetailsController
{
    private static final DocumentDetailsManager documentDetailsManager = new DocumentDetailsManager();

    public static void createDocumentDetails(DocumentDetails documentDetails)
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

}
