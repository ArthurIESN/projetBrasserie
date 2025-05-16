package UI.Document;

import Controller.Document.DocumentController;
import Controller.ProcessType.ProcessTypeController;
import Exceptions.Document.CreateDocumentException;
import Exceptions.ProcessType.GetAllProcessTypesException;
import Model.Document.Document;
import Model.Document.MakeDocument;
import Model.DocumentDetails.DocumentDetails;
import Model.DocumentDetails.MakeDocumentDetails;
import Model.DocumentStatus.DocumentStatus;
import Model.Item.Item;
import Model.Process.Process;
import Model.ProcessType.ProcessType;
import Model.Vat.Vat;
import UI.Components.Fields.SearchListPanel;
import UI.Components.GridBagLayoutHelper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateDocumentForm extends JPanel {
    private GridBagLayoutHelper gridDocument;
    private ArrayList<ProcessType> processTypes;
    private SearchListPanel<ProcessType> processTypeSearch;
    private DocumentModelPanel documentModelPanel;

    public CreateDocumentForm(){
        setLayout(new BorderLayout());
        gridDocument = new GridBagLayoutHelper();
        documentModelPanel = new DocumentModelPanel(false);
        JLabel titleLabel = new JLabel("Create Document");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // Création de la liste de process pour la liste
        processTypes = new ArrayList<>();

        try{
            processTypes = ProcessTypeController.getAllTypes();
        }catch (GetAllProcessTypesException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


        gridDocument.addField(titleLabel);


        if(!processTypes.isEmpty()){
            processTypeSearch = new SearchListPanel<>(processTypes,ProcessType::getLabel);
            processTypeSearch.getSearchField().setPlaceholder("Select Process Type");
            gridDocument.addField(processTypeSearch);
        }

        processTypeSearch.onSelectedItemChange(processTypeChange -> {
            documentModelPanel.setTypeDocument(processTypeSearch.getSelectedItem().getLabel());
            documentModelPanel.loadDataAndShowProcesses(processTypeSearch.getSelectedItem().getId());
            documentModelPanel.update();
        });

        documentModelPanel.setTextButton("Create Document");


        gridDocument.addField(documentModelPanel);

        documentModelPanel.onButtonClicked(e -> createDocument());

        add(gridDocument, BorderLayout.CENTER);

    }

    private void createDocument(){
        if(documentModelPanel.isDocumentInvalid()) return;

        Document document = MakeDocument.getDocument(
                null,
                documentModelPanel.getLabelField().getText(),
                documentModelPanel.getDateField().getDate(),
                null,
                documentModelPanel.getReduction(),
                (String) documentModelPanel.getComboBoxValidity().getSelectedItem(),
                documentModelPanel.getIsDelivered(),
                documentModelPanel.getDeliveryDateField().getDate(),
                documentModelPanel.getCheckBoxDepositIsPaid().isSelected(),
                documentModelPanel.getDepositAmount(),
                documentModelPanel.getDesiredDeliveryDate().getDate(),
                ((Vat) documentModelPanel.getComboBoxVat().getSelectedItem()).getRate(),
                documentModelPanel.getTotalIncludingTax(),
                documentModelPanel.getTotalExcludingTax(),
                null,
                (DocumentStatus) documentModelPanel.getDocumentStatusSearch().getSelectedItem(),
                null,
                (Process) documentModelPanel.getProcessesSearch().getSelectedItem()
        );

        try{
            DocumentController.createDocument(document);
            JOptionPane.showMessageDialog(this,"Document created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }catch (CreateDocumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        if(!documentModelPanel.getMultipleSelectionListItems().getSelectedItems().isEmpty()){
            ArrayList<Item> items = documentModelPanel.getMultipleSelectionListItems().getSelectedItems();

            for (Item item : items){
                // @todo faire la création de document_details
                float newQuantity = documentModelPanel.getNumberFieldHashMap().get(item.getId()).getFloat();
                // Code pour l'ajout d'une ligne dans document_details en rapport avec chaque item
                // mais avec toujours le même id de document
               /* DocumentDetails documentDetails = MakeDocumentDetails.getDocumentDetails(
                        null,
                        item.getLabel(),
                        item.getCurrentQuantity(),
                        newQuantity,
                        item.getPrice(),
                        document.getId(),
                        item.getId()
                );*/
            }
        }
    }

}
