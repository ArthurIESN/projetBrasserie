package UI.Document;

import Controller.ProcessType.ProcessTypeController;
import Exceptions.ProcessType.GetAllProcessTypesException;
import Model.ProcessType.ProcessType;
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

        });


        gridDocument.addField(documentModelPanel);

        add(gridDocument, BorderLayout.CENTER);

    }
}
