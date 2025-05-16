package Controller.Document;

import BusinessLogic.Document.DocumentManager;

import Exceptions.Document.GetAllDocumentsException;
import Exceptions.Document.UpdateDocumentException;
import Exceptions.Document.CreateDocumentException;
import Model.Document.Document;
import Model.Item.Item;

import java.util.ArrayList;

public class DocumentController
{
    private static final DocumentManager documentManager = new DocumentManager();

    public static ArrayList<Document> getAllCurrentCommandsForAnItem(Item item)
    {
        return documentManager.getAllCurrentCommandsForAnItem(item);
    }

    public static int createDocument(Document document) throws CreateDocumentException
    {
        return documentManager.createDocument(document);
    }

    public static void updateDocument(Document document)throws UpdateDocumentException
    {
        documentManager.updateDocument(document);
    }

    public static void deleteDocument(Integer id)
    {
        documentManager.deleteDocument(id);
    }

    public static Document getDocument(Integer id)
    {
        return documentManager.getDocument(id);
    }

    public static ArrayList<Document> getAllDocuments() throws GetAllDocumentsException
    {
        return documentManager.getAllDocuments();
    }
}
