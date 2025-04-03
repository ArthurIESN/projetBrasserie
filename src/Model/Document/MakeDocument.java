package Model.Document;

import Model.CollectionAgency;
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
                                       Float vatAmount, Float totalInclusiveOfTaxe, Float totalVat,
                                       Float totalExclVat, CollectionAgency collectionAgency,
                                       DocumentStatus documentStatus, DeliveryTruck deliveryTruck, Process process){
        if(documentMap.containsKey(id)){
            return documentMap.get(id);
        }else{
            Document document =  new Document(id, label, date,deadline,reduction,validity,
                    isDelivered,deliveryDate,depositIsPaid,depositAmount,desiredDeliveryDate,vatAmount,
                    totalInclusiveOfTaxe,totalVat,totalExclVat,collectionAgency,deliveryTruck,process,documentStatus);
            documentMap.put(id,document);
            return document;
        }
    }
}
