package BusinessLogic.Document;

import DataAccess.Document.DocumentDBAccess;

import Exceptions.Document.DeleteDocumentException;
import Exceptions.Document.GetAllCurrentCommandsForAnItemException;

import Exceptions.Document.GetAllDocumentsException;
import Exceptions.Document.UpdateDocumentException;
import Exceptions.Search.GetDocumentWithSpecificEventException;
import Model.Document.Document;
import Model.Item.Item;

import DataAccess.Document.DocumentDataAccess;

import Exceptions.Document.CreateDocumentException;
import Model.Locality.Locality;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DocumentManager
{
    private final DocumentDataAccess documentDataAccess = new DocumentDBAccess();

    public ArrayList<Document> getAllCurrentCommandsForAnItem(Item item) throws GetAllCurrentCommandsForAnItemException
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

    public void deleteDocument(Integer id) throws DeleteDocumentException
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

    public ArrayList<Document> getDocumentsWithSpecificEvent(int idItem, int idEvent, int quantity, int year) throws GetDocumentWithSpecificEventException
    {
        return documentDataAccess.getDocumentsWithSpecificEvent(idItem, idEvent, quantity, year);
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

    public float[] calculVat(float unitPrice, float quantity, float vatRate) {
        float[] result = new float[3];

        float totalExcludingTax = unitPrice * quantity;
        float totalIncludingTax = totalExcludingTax * (1 + vatRate / 100);
        float vatAmount = totalIncludingTax - totalExcludingTax;

        result[0] = totalExcludingTax;
        result[1] = totalIncludingTax;
        result[2] = vatAmount;

        return result;
    }


    public float[] calculTotalVat(float reduction,float depositAmount, HashMap<Integer,ArrayList<JLabel>> vatItemHashMap, float vatRate) {
        float totalExcludingTax = 0;
        float totalIncludingTax = 0;
        float totalVatAmount = 0;

        for (Map.Entry<Integer, ArrayList<JLabel>> entry : vatItemHashMap.entrySet()) {
            ArrayList<JLabel> labelList = entry.getValue();

            for (int i = 0; i < labelList.size(); i++) {
                float value = Float.parseFloat(labelList.get(i).getText().replace(",", "."));
                if (i == 0) {
                    totalExcludingTax += value;
                } else if (i == 1) {
                    totalIncludingTax += value;
                } else if (i == 2) {
                    totalVatAmount += value;
                }
            }
        }

        if (reduction > 0) {
            float reductionAmount = (totalExcludingTax * reduction) / 100;
            totalExcludingTax -= reductionAmount;

            totalVatAmount = totalExcludingTax * (vatRate / 100);

            totalIncludingTax = totalExcludingTax + totalVatAmount;
        }

        if (depositAmount > 0) {
            totalIncludingTax -= depositAmount;

            if (totalIncludingTax < 0) {
                totalIncludingTax = 0;
            }
        }

        return new float[]{totalExcludingTax, totalIncludingTax, totalVatAmount};
    }
}
