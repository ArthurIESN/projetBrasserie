package BusinessLogic.Document;

import DataAccess.Document.DocumentDBAccess;
import Model.Document.Document;
import Model.Item.Item;

import DataAccess.Document.DocumentDataAccess;

import java.util.ArrayList;


public class DocumentManager
{
    private final DocumentDataAccess documentDataAccess = new DocumentDBAccess();

    public ArrayList<Document> getAllCurrentCommandsForAnItem(Item item)
    {
        return documentDataAccess.getAllCurrentCommandsForAnItem(item);
    }

    public int createDocument(Document document)
    {
        return documentDataAccess.createDocument(document);
    }

    public void updateDocument(Document document)
    {
        documentDataAccess.updateDocument(document);
    }

    public void deleteDocument(Integer id)
    {
        documentDataAccess.deleteDocument(id);
    }

    public Document getDocument(Integer id)
    {
        return documentDataAccess.getDocument(id);
    }

    public ArrayList<Document> getAllDocuments()
    {
        return documentDataAccess.getAllDocuments();
    }
}
