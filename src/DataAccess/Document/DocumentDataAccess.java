package DataAccess.Document;

import Exceptions.Document.CreateDocumentException;
import Model.Document.Document;
import Model.Item.Item;

import java.util.ArrayList;

public interface DocumentDataAccess
{
    ArrayList<Document> getAllCurrentCommandsForAnItem(Item item);

    int createDocument(Document document) throws CreateDocumentException;
    void updateDocument(Document document);
    void deleteDocument(Integer id);
    Document getDocument(Integer id);
    ArrayList<Document> getAllDocuments();

}
