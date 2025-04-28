package BusinessLogic.Tasks.RestockItem;

import Model.Document.Document;
import Model.Item.Item;
import UI.BusinessTasks.RestockItemPanel.RestockItem;
import Controller.Document.DocumentController;

import java.util.ArrayList;
import java.util.Date;

public class RestockItemLogic
{
    RestockItem calculateRestockQuantityAndDate(Item item, Date date)
    {
        // get all current command for this item
        ArrayList<Document> documents = DocumentController.getAllCurrentCommandsForAnItem(item);

        return null;
    }
}
