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
}
