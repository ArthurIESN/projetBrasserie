package DataAccess.Document;

import Exceptions.Document.CreateDocumentException;
import Exceptions.Document.DeleteDocumentException;
import Exceptions.Document.UpdateDocumentException;
import Exceptions.Document.GetAllCurrentCommandsForAnItemException;
import Exceptions.Search.GetDocumentWithSpecificEventException;
import Model.Document.Document;
import Model.Item.Item;

import java.util.ArrayList;

public interface DocumentDataAccess
{
    ArrayList<Document> getAllCurrentCommandsForAnItem(Item item) throws GetAllCurrentCommandsForAnItemException;

    int createDocument(Document document) throws CreateDocumentException;
    void updateDocument(Document document) throws UpdateDocumentException;
    void deleteDocument(Integer id) throws DeleteDocumentException;
    Document getDocument(Integer id);
    ArrayList<Document> getAllDocuments();

    ArrayList<Document> getDocumentsWithSpecificEvent(int idItem, int idEvent, int quantity, int year) throws GetDocumentWithSpecificEventException;

}
