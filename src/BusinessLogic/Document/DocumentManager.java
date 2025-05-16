package BusinessLogic.Document;

import DataAccess.Document.DocumentDBAccess;
import Exceptions.Document.GetAllDocumentsException;
import Exceptions.Document.UpdateDocumentException;
import Model.Document.Document;
import Model.Item.Item;

import DataAccess.Document.DocumentDataAccess;

import Exceptions.Document.CreateDocumentException;

import java.util.ArrayList;


public class DocumentManager
{
    private final DocumentDataAccess documentDataAccess = new DocumentDBAccess();

    public ArrayList<Document> getAllCurrentCommandsForAnItem(Item item)
    {
        return documentDataAccess.getAllCurrentCommandsForAnItem(item);
    }

    public int createDocument(Document document) throws CreateDocumentException
    {
        return documentDataAccess.createDocument(document);
    }

    public void updateDocument(Document document) throws UpdateDocumentException
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

    public ArrayList<Document> getAllDocuments() throws GetAllDocumentsException
    {
        return documentDataAccess.getAllDocuments();
    }
}
