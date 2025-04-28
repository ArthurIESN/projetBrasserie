package Controller.Document;

import BusinessLogic.Document.DocumentManager;

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


}
