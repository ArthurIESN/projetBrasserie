package DataAccess.Document;

import Exceptions.Document.CreateDocumentException;
import Exceptions.Document.DeleteDocumentException;
import Exceptions.Document.UpdateDocumentException;
import Model.Document.Document;
import Model.Item.Item;

import java.util.ArrayList;

public interface DocumentDataAccess
{
    ArrayList<Document> getAllCurrentCommandsForAnItem(Item item);

    int createDocument(Document document) throws CreateDocumentException;
    void updateDocument(Document document) throws UpdateDocumentException;
    void deleteDocument(Integer id) throws DeleteDocumentException;
    Document getDocument(Integer id);
    ArrayList<Document> getAllDocuments();

}
