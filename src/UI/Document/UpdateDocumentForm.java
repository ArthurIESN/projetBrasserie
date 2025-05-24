package UI.Document;

import Controller.Document.DocumentController;
import Exceptions.Document.DocumentException;
import Exceptions.Document.UpdateDocumentException;
import Model.DeliveryTruck.DeliveryTruck;
import Model.Document.Document;
import Model.DocumentStatus.DocumentStatus;
import Model.Process.Process;
import UI.Models.Document.DocumentObserver;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateDocumentForm extends JPanel implements DocumentObserver {
    private final DocumentModelPanel documentModelPanel;
    private final DocumentPanel documentPanel;

    public UpdateDocumentForm(DocumentPanel documentPanel){
        this.documentPanel = documentPanel;
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Update Document");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title,BorderLayout.NORTH);

        documentPanel.addObserver(this);

        documentModelPanel = new DocumentModelPanel(true);
        documentModelPanel.setTextButton("Update Document");

        documentModelPanel.onButtonClicked(e -> updateProcess());

        documentModelPanel.onSearchDocumentChange(e -> updateDocumentSearch());

        add(documentModelPanel);

    }

    private void updateProcess(){
        Document document = documentModelPanel.getDocumentSearch().getSelectedItem();
        if(document == null){
            JOptionPane.showMessageDialog(this, "Please select a document", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(documentModelPanel.isDocumentInvalid()) return;

        Process process = documentModelPanel.getProcessesSearch().getSelectedItem();
        DocumentStatus documentStatus = documentModelPanel.getDocumentStatusSearch().getSelectedItem();
        DeliveryTruck deliveryTruck = documentModelPanel.getDeliveryTruckSearch().getSelectedItem();

        try
        {
            document.setLabel(documentModelPanel.getLabelField().getText());
            document.setDate(new Date());
        }catch (DocumentException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        document.setIsDelivered(documentModelPanel.getCheckBoxIsDelivered().isSelected());
        Date date = documentModelPanel.getDeliveryDateField().getDate();
        document.setDeliveryDate(date);
        document.setDesiredDeliveryDate(documentModelPanel.getDesiredDeliveryDate().getDate());
        document.setReduction(documentModelPanel.getReductionField().getFloat());
        document.setValidity(documentModelPanel.getComboBoxValidity().getSelectedItem());
        document.setDepositIsPaid(documentModelPanel.getCheckBoxDepositIsPaid().isSelected());
        document.setDepositAmount(documentModelPanel.getDepositAmountField().getFloat());
        document.setProcess(process);
        document.setDocumentStatus(documentStatus);
        document.setDeliveryTruck(deliveryTruck);

        try{
            DocumentController.updateDocument(document);
            JOptionPane.showMessageDialog(this, "Document updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            documentPanel.navbarForceClick(2);
        }catch(UpdateDocumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateDocumentSearch(){
        Document selectedDocument = documentModelPanel.getDocumentSearch().getSelectedItem();

        documentModelPanel.loadDataAndShowProcesses(selectedDocument.getProcess().getType().getId());

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        documentModelPanel.getDateField().updateText(selectedDocument.getDate() != null ? formatter.format(selectedDocument.getDate()) : "");
        documentModelPanel.getLabelField().updateText(selectedDocument.getLabel());
        documentModelPanel.getCheckBoxIsDelivered().setSelected(selectedDocument.getIsDelivered());
        documentModelPanel.getDocumentStatusSearch().forceSetSelectedItem(selectedDocument.getDocumentStatus());
        documentModelPanel.getProcessesSearch().forceSetSelectedItem(selectedDocument.getProcess());
        documentModelPanel.getDeliveryDateField().updateText(selectedDocument.getDeliveryDate() != null ? formatter.format(selectedDocument.getDeliveryDate()) : "");
        documentModelPanel.getDesiredDeliveryDate().updateText(selectedDocument.getDesiredDeliveryDate() != null ? formatter.format(selectedDocument.getDesiredDeliveryDate()) : "");
        documentModelPanel.getReductionField().updateText(String.valueOf(selectedDocument.getReduction()));
        documentModelPanel.getComboBoxValidity().setSelectedItem(selectedDocument.getValidity());
        documentModelPanel.getCheckBoxDepositIsPaid().setSelected(selectedDocument.getDepositIsPaid());
        documentModelPanel.getDepositAmountField().updateText(String.valueOf(selectedDocument.getDepositAmount()));
    }

    @Override
    public void update(Document document) {
        documentModelPanel.getDocumentSearch().forceSetSelectedItem(document);
    }
}
