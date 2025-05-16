package Model.Document;

import Exceptions.Document.DocumentException;
import Model.CollectionAgency.CollectionAgency;
import Model.DeliveryTruck.DeliveryTruck;
import Model.DocumentStatus.DocumentStatus;
import Model.Process.Process;

import java.util.Date;
import java.util.HashMap;

public class MakeDocument {

    private static final HashMap<Integer, Document> documentMap = new HashMap<>();

    public static Document getDocument(Integer id, String label, Date date, Date deadline, Float reduction,
                                       String validity, Boolean isDelivered, Date deliveryDate,
                                       Boolean depositIsPaid, Float depositAmount, Date desiredDeliveryDate,
                                       Float totalInclusiveOfTaxe, Float totalVat,
                                       Float totalExclVat, CollectionAgency collectionAgency,
                                       DocumentStatus documentStatus, DeliveryTruck deliveryTruck, Process process)
    {
        int documentHash = Document.hashCode(id, label, date, deadline, reduction, validity,
                isDelivered, deliveryDate, depositIsPaid, depositAmount, desiredDeliveryDate,
                totalInclusiveOfTaxe, totalVat, totalExclVat, collectionAgency, deliveryTruck, process, documentStatus);

        if(documentMap.containsKey(documentHash))
        {
            return documentMap.get(documentHash);
        }
        else
        {
            try
            {
                Document document = new Document(id, label, date, deadline, reduction, validity,
                        isDelivered, deliveryDate, depositIsPaid, depositAmount, desiredDeliveryDate,
                        totalInclusiveOfTaxe, totalVat, totalExclVat, collectionAgency, deliveryTruck, process, documentStatus);
                documentMap.put(documentHash, document);
                return document;
            }
            catch (DocumentException e)
            {
                System.out.println(e.getMessage());
                return null;
            }

        }
    }
}
