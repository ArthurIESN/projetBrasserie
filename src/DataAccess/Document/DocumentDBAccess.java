package DataAccess.Document;

import Model.Document.Document;
import Model.Item.Item;

import java.util.ArrayList;

public class DocumentDBAccess implements DocumentDataAccess
{
    public ArrayList<Document> getAllCurrentCommandsForAnItem(Item item)
    {
        // Document -> Document_details(id_document) -> item_document_details -> item

        String query = "SELECT * FROM document " +
                "WHERE document.id_document IN (" +
                "    SELECT dd.id_document " +
                "    FROM Document_details dd " +
                "    JOIN item_document_details idd ON dd.id_document_details = idd.id_document_details " +
                "    WHERE idd.id_item = ?" +
                ") AND d.status = 'current'";

    return  null;
    }
}
