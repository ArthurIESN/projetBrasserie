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
import UI.Components.Fields.SearchListPanel;
import UI.Components.GridBagLayoutHelper;
import UI.Models.CollectionAgencyEnhancedTableModel;
import UI.Models.DeliveryTruckEnhancedTableModel;
import UI.Models.Document.DocumentEnhancedTableModel;
import UI.Models.Document.DocumentObserver;
import UI.Models.DocumentStatusEnhancedTableModel;
import UI.Models.ProcessEnhancedTableModel;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DeleteDocumentForm extends JPanel implements DocumentObserver {
    private final SearchListPanel<Document> documentSearch;

    public DeleteDocumentForm(DocumentPanel documentPanel){
        GridBagLayoutHelper gridDeleteDocument = new GridBagLayoutHelper();
        ArrayList<Document> documents = new ArrayList<>();

        try{
            documents = DocumentController.getAllDocuments();
        } catch (GetAllDocumentsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        documentPanel.addObserver(this);
        documentSearch = new SearchListPanel<>(documents, searchDocument -> searchDocument.getLabel());
        documentSearch.setPreferredSize(new java.awt.Dimension(500, documentSearch.getPreferredSize().height));
        documentSearch.getSearchField().setPlaceholder("Search for a document");
        gridDeleteDocument.addField("Document",documentSearch);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            Document selectedDocument = documentSearch.getSelectedItem();
            if (selectedDocument == null) {
                JOptionPane.showMessageDialog(null, "Please select a document to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this document?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.NO_OPTION) {
                return;
            }

            try {
                DocumentController.deleteDocument(selectedDocument.getId());
                JOptionPane.showMessageDialog(null, "Document deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        gridDeleteDocument.addField(deleteButton);
        add(gridDeleteDocument, BorderLayout.CENTER);

        TableModelMaker tableModelMaker = new TableModelMaker();
        DocumentEnhancedTableModel documentTableModel = new DocumentEnhancedTableModel();
        DocumentStatusEnhancedTableModel documentStatusTableModel = new DocumentStatusEnhancedTableModel();
        CollectionAgencyEnhancedTableModel collectionAgencyTableModel = new CollectionAgencyEnhancedTableModel();
        DeliveryTruckEnhancedTableModel deliveryTruckTableModel = new DeliveryTruckEnhancedTableModel();
        ProcessEnhancedTableModel processTableModel = new ProcessEnhancedTableModel();


        tableModelMaker.addTableModel(documentTableModel);
        tableModelMaker.addTableModel(collectionAgencyTableModel);
        tableModelMaker.addTableModel(deliveryTruckTableModel);
        tableModelMaker.addTableModel(processTableModel);
        tableModelMaker.addTableModel(documentStatusTableModel);

        JEnhancedTableScrollPanel tableScrollPanel = new JEnhancedTableScrollPanel(tableModelMaker, this);
        tableModelMaker.setTable(tableScrollPanel);

        documentSearch.onSelectedItemChange(e -> {
            Document selectedDocument = documentSearch.getSelectedItem();

            ArrayList<Document> documents1 = new ArrayList<>();
            documents1.add(selectedDocument);
            ArrayList<DocumentStatus> documentStatuses = Utils.transformData(selectedDocument, Document::getDocumentStatus);
            ArrayList<CollectionAgency> collectionAgencies = Utils.transformData(selectedDocument, Document::getCollectionAgency);
            ArrayList<DeliveryTruck> deliveryTrucks = Utils.transformData(selectedDocument, Document::getDeliveryTruck);
            ArrayList<Process> processes = Utils.transformData(selectedDocument, Document::getProcess);

            documentTableModel.setData(documents1);
            documentStatusTableModel.setData(documentStatuses);
            collectionAgencyTableModel.setData(collectionAgencies);
            deliveryTruckTableModel.setData(deliveryTrucks);
            processTableModel.setData(processes);

            tableModelMaker.setTable(tableScrollPanel);

            if(selectedDocument != null) {
                tableScrollPanel.updateModel(tableModelMaker);
            }

        });
        add(tableScrollPanel);
    }

    @Override
    public void update(Document document) {documentSearch.forceSetSelectedItem(document);}
}
