package UI.Search.Document;

import Controller.AppController;
import Controller.SearchController;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Event.GetEventsWithItemException;
import Exceptions.Item.GetAllItemsException;
import Exceptions.Search.GetDocumentWithSpecificEventException;
import Exceptions.Search.GetQuantityItemWithSpecificEventException;
import Model.Document.Document;
import Model.Event.Event;
import Model.Item.Item;
import UI.Components.EnhancedTable.JEnhancedTableScrollPanel;
import UI.Components.EnhancedTable.TableModelMaker;
import UI.Components.GridBagLayoutHelper;
import UI.Components.Fields.SearchListPanel;
import UI.Components.StepManager;
import UI.Models.Document.DocumentEnhancedTableModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SearchDocumentWithEventForm extends JPanel {
    private JLabel title;
    private JPanel filterLabelPanel;
    private JLabel filterLabel;
    private StepManager stepManager;
    private List<String> filters = new ArrayList<>();
    private List<Integer> years = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    private List<Float> quantities = new ArrayList<>();
    private SearchListPanel<Item> itemSearch;
    private SearchListPanel<Event> eventSearch;
    private SearchListPanel<Float> quantitySearch;
    private SearchListPanel<Integer> yearSearch;
    private TableModelMaker tableModelMaker;
    private DocumentEnhancedTableModel documentTableModel;
    private JEnhancedTableScrollPanel tableScrollPanel;
    private JButton searchButton;

    public SearchDocumentWithEventForm(){

        setLayout(new BorderLayout());
        title = new JLabel("Search for a supplier order with specific filters");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // top, left, bottom, right

        filterLabel = new JLabel(String.join("", filters));

        searchButton = new JButton("Search");
        searchButton.setVisible(false);

        filterLabelPanel = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
        filterLabelPanel.setBorder(border);
        filterLabelPanel.add(filterLabel);


        GridBagLayoutHelper filterPanel = new GridBagLayoutHelper();

        try
        {
            items = AppController.getAllItems();
        }catch (GetAllItemsException e)
        {
            System.out.println(e.getMessage());
        }

        itemSearch = new SearchListPanel<>(items, Item::getLabel);
        eventSearch = new SearchListPanel<>(new ArrayList<>(), Event::getLabel);
        quantitySearch = new SearchListPanel<>(new ArrayList<>(), quantity -> Float.toString(quantity));
        yearSearch = new SearchListPanel<>(new ArrayList<>(), year -> Integer.toString(year));


        Component[] stepsList = new Component[] {itemSearch,eventSearch,quantitySearch,yearSearch,searchButton};


        filterPanel.addField(title);
        filterPanel.addField(filterLabelPanel);

        filterPanel.addField(itemSearch);
        filterPanel.addField(eventSearch);
        filterPanel.addField(quantitySearch);
        filterPanel.addField(yearSearch);
        filterPanel.addField(searchButton);


        stepManager = new StepManager(stepsList);

        itemSearch.onSelectedItemChange(itemChanged ->
        {
            stepManager.completeStep(0);
        });

        eventSearch.onSelectedItemChange(eventChanged -> {
            stepManager.completeStep(1);
        });

        quantitySearch.onSelectedItemChange(quantityChanged -> {
            stepManager.completeStep(2);
        });

        yearSearch.onSelectedItemChange(yearChanged -> {
            stepManager.completeStep(3);
        });


        stepManager.onStepShown(1, this::functionCalledWhenStepEventIsShown);
        stepManager.onStepShown(2, this::functionCalledWhenStepQuantitiesIsShown);
        stepManager.onStepShown(3, this::functionCalledWhenStepYearsIsShown);
        stepManager.onStepShown(4, this::functionCalledWhenStepButtonIsShown);


        tableModelMaker = new TableModelMaker();
        documentTableModel = new DocumentEnhancedTableModel(new ArrayList<>());

        tableModelMaker.addTableModel(documentTableModel);

        tableScrollPanel = new JEnhancedTableScrollPanel(tableModelMaker,this);
        tableModelMaker.setTable(tableScrollPanel);

        searchButton.addActionListener(e -> {
            try{
                ArrayList<Document> documents = SearchController.getDocumentsWithSpecificEvent(itemSearch.getSelectedItem().getId(),
                        eventSearch.getSelectedItem().getId(),quantitySearch.getSelectedItem(),
                        yearSearch.getSelectedItem());
                documentTableModel.setData(documents);
                tableScrollPanel.updateModel(tableModelMaker);
                // @todo : gérer les erreurs
            }catch (DatabaseConnectionFailedException | GetDocumentWithSpecificEventException err){
                System.err.println(err.getMessage());
            }
        });


        // Permet d'ajouter un slide entre les deux parties pour agrandir ou diminuer l'une ou l'autre.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(filterPanel);
        splitPane.setBottomComponent(tableScrollPanel);
        splitPane.setResizeWeight(0.70);
        splitPane.setDividerSize(5);

        add(splitPane,BorderLayout.CENTER);

    }

    private void functionCalledWhenStepEventIsShown(){
        try {
            int itemId = itemSearch.getSelectedItem().getId();
            events = SearchController.getEventsWithSpecificItem(itemId);
        }catch (DatabaseConnectionFailedException | GetEventsWithItemException e){
            System.out.println(e.getMessage());
        }

        clearPanelFilter();
        filters(itemSearch.getSelectedItem().getLabel());

        if(!events.isEmpty()){
            eventSearch.setData(events);
        }else {
            JOptionPane.showMessageDialog(null, "No event was found for the selected product.","No event found",JOptionPane.INFORMATION_MESSAGE);
            stepManager.stop();
        }
    }
    private void functionCalledWhenStepQuantitiesIsShown(){
        try {
            int idEventSelected = eventSearch.getSelectedItem().getId();
            quantities = SearchController.getQuantityItemWithSpecificEvent(idEventSelected);
        }catch (DatabaseConnectionFailedException | GetQuantityItemWithSpecificEventException e){
            System.out.println(e.getMessage());
        }

        clearPanelFilter();
        filters(eventSearch.getSelectedItem().getLabel());

        if(!quantities.isEmpty()){
            quantitySearch.setData(quantities);

        }else {
            JOptionPane.showMessageDialog(null, "No quantities was found for the selected event.","No quantities found",JOptionPane.INFORMATION_MESSAGE);
            stepManager.stop();
        }
    }

    private void functionCalledWhenStepYearsIsShown(){
        try{
            int idEventSelected = eventSearch.getSelectedItem().getId();
            years = SearchController.getDatesEvents(idEventSelected);
        }catch (DatabaseConnectionFailedException e){
            System.out.println(e.getMessage());
        }
        clearPanelFilter();
        filters(quantitySearch.getSelectedItem().toString());

        if(!years.isEmpty()){
            yearSearch.setData(years);
        }else {
            JOptionPane.showMessageDialog(null, "No years was found for the selected quantity.","No years found",JOptionPane.INFORMATION_MESSAGE);
            stepManager.stop();
        }
    }

    private void functionCalledWhenStepButtonIsShown(){
        clearPanelFilter();
        filters(yearSearch.getSelectedItem().toString());
    }

    private void setFilterLabel(){
        filterLabel.setText("<html><span style='font-family: Arial; font-size: 9px; font-weight: none; color: #136F63;'>"
                + String.join(" ", filters) + "</span></html>");
    }

    private void filters(String label) {
        String formattedLabel = label + " / ";

        if (stepManager.getCurrentStep() < filters.size())
        {
            filters.set(stepManager.getCurrentStep(), formattedLabel);
        } else {
            filters.add(formattedLabel);
        }

        setFilterLabel();
    }

    private void clearPanelFilter()
    {
        for(int i = stepManager.getCurrentStep() - 1; i < filters.size(); i++)
        {
            filters.remove(i);
            i--;
        }
        setFilterLabel();
    }
}