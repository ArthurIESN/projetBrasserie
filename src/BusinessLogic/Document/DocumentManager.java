package BusinessLogic.Document;

import DataAccess.Document.DocumentDBAccess;
import Exceptions.Document.GetAllDocumentsException;
import Exceptions.Document.UpdateDocumentException;
import Model.Document.Document;
import Model.Item.Item;

import DataAccess.Document.DocumentDataAccess;

import Exceptions.Document.CreateDocumentException;
import Model.Locality.Locality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DocumentManager
{
    private final DocumentDataAccess documentDataAccess = new DocumentDBAccess();

    public ArrayList<Document> getAllCurrentCommandsForAnItem(Item item)
    {
        return documentDataAccess.getAllCurrentCommandsForAnItem(item);
    }

    public int createDocument(Document document) throws CreateDocumentException
    {
        return documentDataAccess.createDocument(document);
    }

    public void updateDocument(Document document) throws UpdateDocumentException
    {
        documentDataAccess.updateDocument(document);
    }

    public void deleteDocument(Integer id)
    {
        documentDataAccess.deleteDocument(id);
    }

    public Document getDocument(Integer id)
    {
        return documentDataAccess.getDocument(id);
    }

    public ArrayList<Document> getAllDocuments() throws GetAllDocumentsException
    {
        return documentDataAccess.getAllDocuments();
    }

    public float[] calculateTaxes(HashMap<Item, Integer> items, Locality locality)
    {
        /*
         * 0 - Total Vat
         * 1 -  Total VAT Inclusive
         * 2 -  Total Delivery Cost
         * 3 - Total Price
         */
        float[] values = {0, 0, 0, 0};

        for (Map.Entry<Item, Integer> entry : items.entrySet())
        {
            int quantity = entry.getValue();
            Item item = entry.getKey();

            values[3] += item.getPrice() * quantity;
            values[0] += item.getPrice() * quantity*  (item.getVat().getRate() / 100);
        }

        values[1] = values[3] + values[0];

        if(locality != null)
        {
            values[2] = locality.getCountry().getDeliveryCost();
        }

        values[3] += values[2] + values[0];

        return values;
    }
}
