package UI.Document;

import Controller.DocumentStatus.DocumentStatusController;
import Controller.Item.ItemController;
import Controller.Process.ProcessController;
import Controller.VAT.VATController;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DocumentStatus.GetAllDocumentStatusException;
import Exceptions.Item.GetAllItemsException;
import Exceptions.Process.GetProcessWithSpecificType;
import Exceptions.Vat.GetAllVatsException;
import Model.CollectionAgency;
import Model.DeliveryTruck.DeliveryTruck;
import Model.Document.Document;
import Model.DocumentStatus.DocumentStatus;
import Model.Item.Item;
import Model.Process.Process;
import Model.Vat.Vat;
import UI.Components.Fields.*;
import UI.Components.GridBagLayoutHelper;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import Utils.Utils;

public class DocumentModelPanel extends JPanel {
    private JPanel numberFieldPanel;
    private JEnhancedTextField labelField;
    private JDateField dateField;
    private String typeDocument;
    private SearchListPanel<String> deliveryStatusSearch;
    private SearchListPanel<DocumentStatus> documentStatusSearch;

    private SearchListPanel<Process> processesSearch;

   // ArrayList de tous les processus
    private ArrayList<Process> processes;

    // ???
    private ArrayList<SearchListPanel> searchListPanelsOrderFourn;

    // Liste de tous les composants
    private ArrayList<JComponent> allComponents;

    // HashMap pour les champs de nombre
    private HashMap<Integer, JNumberField> numberFieldHashMap;

    // HashMap pour les types de process
    private HashMap<Integer,ArrayList<Integer>> modelsDocuments;

    public DocumentModelPanel(boolean isUpdate) {
        setLayout(new BorderLayout());

        numberFieldHashMap = new HashMap<>();

        numberFieldPanel = new JPanel();
        numberFieldPanel.setLayout(new BoxLayout(numberFieldPanel, BoxLayout.Y_AXIS));
        numberFieldPanel.setBorder(BorderFactory.createTitledBorder("Number Field"));

        searchListPanelsOrderFourn = new ArrayList<>(Arrays.asList());


        ArrayList<Document> documents = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<CollectionAgency> collectionAgencies = new ArrayList<>();
        ArrayList<DocumentStatus> documentStatuses = new ArrayList<>();
        ArrayList<DeliveryTruck> deliveryTrucks = new ArrayList<>();
        ArrayList<String> deliveryStatusOptions = new ArrayList<>(Arrays.asList("Yes", "No"));
        processes = new ArrayList<>();

        // ArrayList pour les code de tva dans allComponents
        ArrayList<Vat> vats = new ArrayList<>();

        try{
           vats = VATController.getAllVats();
        }catch (GetAllVatsException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        try{
            items = ItemController.getAllItems();
        }catch (GetAllItemsException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


        allComponents = new ArrayList<>();

        JDateField deliveryDateField = new JDateField();
        JDateField desiredDeliveryDate = new JDateField();
        SearchListPanel<Vat> vatSearch = new SearchListPanel<>(vats, Vat::getCode);
        //JNumberField


        // 0
        allComponents.add(deliveryDateField);
        // 1
        allComponents.add(desiredDeliveryDate);
        // 2
        allComponents.add(vatSearch);



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


        MultipleSelectionList<Item> tests = new MultipleSelectionList<>(items,Item::getLabel);

        tests.setOnSelectionChange(selectedItems -> {
            ArrayList<Integer> selectedIds = Utils.transformData(selectedItems, Item::getId);
            updateFieldsQuantity(selectedIds);
        });

        gridDocument.addField(labelField);
        gridDocument.addField(dateField);
        gridDocument.addField(deliveryStatusSearch);
        gridDocument.addField(documentStatusSearch);
        gridDocument.addField(processesSearch);
        gridDocument.addField(tests);
        gridDocument.addField(numberFieldPanel);
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

    public void loadDataAndShowProcesses(Integer id){
            try{
                processes = ProcessController.getProcessWithSpecificType(id);
                if(!processes.isEmpty()){
                    processesSearch.setData(processes);
                    processesSearch.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(this, "No processes found for the selected type.", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch (GetProcessWithSpecificType e){
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }

    public void update(){
        switch (this.typeDocument){
            case "Order":
                break;
        }
    }

    private void updateFieldsQuantity(List<Integer> selectedIds){

        System.out.println(selectedIds);
        for(Integer id : selectedIds){
            if(!numberFieldHashMap.containsKey(id)){
                JNumberField numberField = new JNumberField(JNumberField.NumberType.FLOAT,2);
                numberField.setPlaceholder("Enter quantity");
                numberFieldHashMap.put(id, numberField);
                System.out.println(numberFieldHashMap);
                numberFieldPanel.add(numberField);
            }
        }

        List<Integer> idsToRemove = new ArrayList<>();

        for (Integer id : numberFieldHashMap.keySet()){
            if(!selectedIds.contains(id)){
                JNumberField fieldToRemove = numberFieldHashMap.get(id);
                numberFieldPanel.remove(fieldToRemove);
                idsToRemove.add(id);

            }
        }

        for (Integer id : idsToRemove){
            numberFieldHashMap.remove(id);
        }

        numberFieldPanel.revalidate();
        numberFieldPanel.repaint();

    }
}
