package DataAccess.Document;

import Model.Document.Document;
import Model.Item.Item;

import java.util.ArrayList;

public interface DocumentDataAccess
{
    ArrayList<Document> getAllCurrentCommandsForAnItem(Item item);

    void createDocument(Document document);
    void updateDocument(Document document);
    void deleteDocument(Integer id);
    Document getDocument(Integer id);
    ArrayList<Document> getAllDocuments();

}
