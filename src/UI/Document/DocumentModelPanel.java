package UI.Document;

import Controller.DocumentStatus.DocumentStatusController;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DocumentStatus.GetAllDocumentStatusException;
import Model.CollectionAgency.CollectionAgency;
import Model.DeliveryTruck.DeliveryTruck;
import Model.Document.Document;
import Model.DocumentStatus.DocumentStatus;
import Model.Process.Process;
import UI.Components.Fields.JDateField;
import UI.Components.Fields.JEnhancedTextField;
import UI.Components.Fields.SearchListPanel;
import UI.Components.GridBagLayoutHelper;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DocumentModelPanel extends JPanel {
    private JEnhancedTextField labelField;
    private JDateField dateField;
    private String typeDocument;
    private SearchListPanel<String> deliveryStatusSearch;
    private SearchListPanel<DocumentStatus> documentStatusSearch;
    private SearchListPanel<Process> processesSearch;

    private ArrayList<SearchListPanel> searchListPanelsOrderFourn;

    public DocumentModelPanel(boolean isUpdate) {
        setLayout(new BorderLayout());

        searchListPanelsOrderFourn = new ArrayList<>(Arrays.asList());


        ArrayList<Document> documents = new ArrayList<>();
        ArrayList<CollectionAgency> collectionAgencies = new ArrayList<>();
        ArrayList<DocumentStatus> documentStatuses = new ArrayList<>();
        ArrayList<DeliveryTruck> deliveryTrucks = new ArrayList<>();
        ArrayList<Process> processes = new ArrayList<>();
        ArrayList<String> deliveryStatusOptions = new ArrayList<>(Arrays.asList("Yes", "No"));


        try{
            documentStatuses = DocumentStatusController.getAllDocumentStatus();
        }catch (DatabaseConnectionFailedException | GetAllDocumentStatusException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        Date today = new Date();

        GridBagLayoutHelper gridDocument = new GridBagLayoutHelper();

        labelField = new JEnhancedTextField();
        labelField.setPlaceholder("Label document");
        labelField.setEditable(false);

        dateField = new JDateField();
        dateField.setDate(today);

        deliveryStatusSearch = new SearchListPanel<>(deliveryStatusOptions, status -> status);
        deliveryStatusSearch.getSearchField().setPlaceholder("Select Delivery Status");

        documentStatusSearch = new SearchListPanel<>(documentStatuses, DocumentStatus::getLabel);
        documentStatusSearch.getSearchField().setPlaceholder("Select Document Status");

        processesSearch = new SearchListPanel<>(processes, Process::getLabel);
        processesSearch.setVisible(false);

        gridDocument.addField(labelField);
        gridDocument.addField(dateField);
        gridDocument.addField(deliveryStatusSearch);
        gridDocument.addField(documentStatusSearch);
        gridDocument.addField(processesSearch);
        add(gridDocument, BorderLayout.CENTER);
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
        setLabelField(typeDocument);
    }

    private void setLabelField(String label) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmms");
        String formattedDateTime = now.format(formatter);
        labelField.updateText(label + formattedDateTime);
    }

    public void loadDataAndShowProcesses(){
            //coucou
    }

    public void update(){
        switch (this.typeDocument){
            case "Order":
                break;
        }
    }
}
