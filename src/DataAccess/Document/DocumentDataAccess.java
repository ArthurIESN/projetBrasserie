package DataAccess.Document;

import Model.Document.Document;
import Model.Item.Item;

import java.util.ArrayList;

public interface DocumentDataAccess
{
    ArrayList<Document> getAllCurrentCommandsForAnItem(Item item);
}
