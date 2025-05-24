package UI.Document;

import Controller.Document.DocumentController;
import Exceptions.Document.GetAllDocumentsException;
import Model.CollectionAgency.CollectionAgency;
import Model.DeliveryTruck.DeliveryTruck;
import Model.Document.Document;
import Model.DocumentStatus.DocumentStatus;
import Model.Process.Process;
import UI.Components.EnhancedTable.JEnhancedTableScrollPanel;
import UI.Components.EnhancedTable.TableModelMaker;
import UI.Models.CollectionAgencyEnhancedTableModel;
import UI.Models.DeliveryTruckEnhancedTableModel;
import UI.Models.Document.DocumentEnhancedTableModel;
import UI.Models.DocumentStatusEnhancedTableModel;
import UI.Models.ProcessEnhancedTableModel;
import Utils.Utils;

import javax.swing.*;
import java.util.ArrayList;

public class ReadDocumentForm extends JPanel
{
    public ReadDocumentForm(DocumentPanel documentPanel)
    {
        ArrayList<Document> documents;

        try{
            documents = DocumentController.getAllDocuments();
        }
        catch (GetAllDocumentsException e){
            documents = new ArrayList<>();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        ArrayList<String> menuItems = new ArrayList<>();
        menuItems.add("Update");
        menuItems.add("Delete");

        TableModelMaker tableModelMaker = new TableModelMaker();

        DocumentEnhancedTableModel documentTableModel = new DocumentEnhancedTableModel(documents);

        ArrayList<DocumentStatus> documentStatuses = Utils.transformData(documents, Document::getDocumentStatus);
        DocumentStatusEnhancedTableModel documentStatusTableModel = new DocumentStatusEnhancedTableModel(documentStatuses);

        ArrayList<CollectionAgency> collectionAgencies = Utils.transformData(documents, Document::getCollectionAgency);
        CollectionAgencyEnhancedTableModel collectionAgencyTableModel = new CollectionAgencyEnhancedTableModel(collectionAgencies);

        ArrayList<DeliveryTruck> deliveryTrucks = Utils.transformData(documents, Document::getDeliveryTruck);
        DeliveryTruckEnhancedTableModel deliveryTruckTableModel = new DeliveryTruckEnhancedTableModel(deliveryTrucks);

        ArrayList<Process> processes = Utils.transformData(documents, Document::getProcess);
        ProcessEnhancedTableModel processTableModel = new ProcessEnhancedTableModel(processes);


        tableModelMaker.addTableModel(documentTableModel);
        tableModelMaker.addTableModel(collectionAgencyTableModel);
        tableModelMaker.addTableModel(deliveryTruckTableModel);
        tableModelMaker.addTableModel(processTableModel);
        tableModelMaker.addTableModel(documentStatusTableModel);

        JEnhancedTableScrollPanel tableScrollPanel = new JEnhancedTableScrollPanel(tableModelMaker, this);
        add(tableScrollPanel);
        tableModelMaker.setTable(tableScrollPanel);
        tableScrollPanel.updateModel(tableModelMaker);


        ArrayList<Document> finalDocuments = documents;

        tableScrollPanel.addMenuOnRows(menuItems, action ->
        {
            switch (action.getActionCommand())
            {
                case "Update" -> documentPanel.moveTo(2);
                case "Delete" -> documentPanel.moveTo(3);
            }
            documentPanel.notifyObservers(finalDocuments.get(tableScrollPanel.getTable().getSelectedRow()));
        });
    }
}
