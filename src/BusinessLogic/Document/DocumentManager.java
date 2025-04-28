package BusinessLogic.Document;

import Model.Document.Document;
import Model.Item.Item;

import DataAccess.Document.DocumentDataAccess;

import java.util.ArrayList;


public class DocumentManager
{
    private DocumentDataAccess documentDataAccess;

    public ArrayList<Document> getAllCurrentCommandsForAnItem(Item item)
    {
        return documentDataAccess.getAllCurrentCommandsForAnItem(item);
    }
}
