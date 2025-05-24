package Controller.Document;

import BusinessLogic.Document.DocumentManager;

import Exceptions.Document.GetAllCurrentCommandsForAnItemException;
import Exceptions.Document.GetAllDocumentsException;
import Exceptions.Document.UpdateDocumentException;
import Exceptions.Document.CreateDocumentException;
import Exceptions.Search.GetDocumentWithSpecificEventException;
import Model.Document.Document;
import Model.Item.Item;
import Model.Locality.Locality;

import java.util.ArrayList;
import java.util.HashMap;

public class DocumentController
{
    private static final DocumentManager documentManager = new DocumentManager();

    public static ArrayList<Document> getAllCurrentCommandsForAnItem(Item item) throws GetAllCurrentCommandsForAnItemException
    {
        return documentManager.getAllCurrentCommandsForAnItem(item);
    }

    public static float[] calculateTaxes(HashMap<Item, Integer> items, Locality locality)
    {
        return documentManager.calculateTaxes(items, locality);
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

    public static ArrayList<Document> getDocumentsWithSpecificEvent(int idItem, int idEvent, int quantity, int year) throws GetDocumentWithSpecificEventException
    {
        return documentManager.getDocumentsWithSpecificEvent(idItem, idEvent, quantity, year);
    }
}
