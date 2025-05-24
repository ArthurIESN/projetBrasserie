package UI.Search.Document;

import Controller.DocumentDetails.DocumentDetailsController;
import Controller.Item.ItemController;
import Controller.Event.EventController;
import Controller.Document.DocumentController;
import Exceptions.Event.GetDatesEventsException;
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
    private final JLabel filterLabel;
    private final StepManager stepManager;
    private final ArrayList<String> filters = new ArrayList<>();
    private ArrayList<Integer> years = new ArrayList<>();
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Integer> quantities = new ArrayList<>();
    private final SearchListPanel<Item> itemSearch;
    private final SearchListPanel<Event> eventSearch;
    private final SearchListPanel<Integer> quantitySearch;
    private final SearchListPanel<Integer> yearSearch;
    private final TableModelMaker tableModelMaker;
    private final DocumentEnhancedTableModel documentTableModel;
    private final JEnhancedTableScrollPanel tableScrollPanel;
    private final GridBagLayoutHelper filterPanel;

    public SearchDocumentWithEventForm(){

        setLayout(new BorderLayout());
        JLabel title = new JLabel("Search for a supplier order with specific filters");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        add(title, BorderLayout.NORTH);

        filterLabel = new JLabel("");
        setFilterLabel();
        filterLabel.setPreferredSize(new Dimension(500, 30));
        filterLabel.setForeground(new Color(11, 126, 0));


        JPanel filterLabelWrapperPanel = new JPanel();
        filterLabelWrapperPanel.setLayout(new BorderLayout());
        filterLabelWrapperPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        filterLabelWrapperPanel.add(filterLabel);

        JPanel FilterLabelCenteredPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        FilterLabelCenteredPanel.add(filterLabelWrapperPanel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setSize(new Dimension(500, 100));

        List<Item> items = new ArrayList<>();
        try
        {
            items = ItemController.getAllItems();
        }catch (GetAllItemsException e)
        {
            System.out.println(e.getMessage());
        }

        filterPanel = new GridBagLayoutHelper();

        JButton searchButton = new JButton("Search");
        searchButton.setVisible(false);

        itemSearch = new SearchListPanel<>(items, Item::getLabel);
        itemSearch.getSearchField().setPlaceholder("Search for an item");
        itemSearch.getSearchField().setCanClear(false);

        eventSearch = new SearchListPanel<>(new ArrayList<>(), Event::getLabel);
        eventSearch.getSearchField().setPlaceholder("Search for an event");
        eventSearch.getSearchField().setCanClear(false);

        quantitySearch = new SearchListPanel<>(new ArrayList<>(), quantity -> Integer.toString(quantity));
        quantitySearch.getSearchField().setPlaceholder("Search for a quantity");
        quantitySearch.getSearchField().setCanClear(false);

        yearSearch = new SearchListPanel<>(new ArrayList<>(), year -> Integer.toString(year));
        yearSearch.getSearchField().setPlaceholder("Search for a year");
        yearSearch.getSearchField().setCanClear(false);

        filterPanel.addField(itemSearch);
        filterPanel.addField(eventSearch);
        filterPanel.addField(quantitySearch);
        filterPanel.addField(yearSearch);
        filterPanel.addField(searchButton);

        Component[] stepsList = new Component[] {itemSearch,eventSearch,quantitySearch,yearSearch, searchButton};
        stepManager = new StepManager(stepsList);

        itemSearch.onSelectedItemChange(itemChanged ->
        {
            stepManager.completeStep(0);
            filterPanel.scrollToBottom();
        });

        eventSearch.onSelectedItemChange(eventChanged -> {
            stepManager.completeStep(1);
            filterPanel.scrollToBottom();
        });

        quantitySearch.onSelectedItemChange(quantityChanged -> {
            stepManager.completeStep(2);
            filterPanel.scrollToBottom();
        });

        yearSearch.onSelectedItemChange(yearChanged -> {
            stepManager.completeStep(3);
            filterPanel.scrollToBottom();
        });

        stepManager.onStepShown(1, this::onEventStepShown);
        stepManager.onStepShown(2, this::onQuantityStepShown);
        stepManager.onStepShown(3, this::onYearsStepShown);
        stepManager.onStepShown(4, this::onButtonStepShown);

        tableModelMaker = new TableModelMaker();
        documentTableModel = new DocumentEnhancedTableModel(new ArrayList<>());

        tableModelMaker.addTableModel(documentTableModel);

        tableScrollPanel = new JEnhancedTableScrollPanel(tableModelMaker,this);
        tableModelMaker.setTable(tableScrollPanel);

        searchButton.addActionListener(e -> {
            try{
                ArrayList<Document> documents = DocumentController.getDocumentsWithSpecificEvent(itemSearch.getSelectedItem().getId(),
                        eventSearch.getSelectedItem().getId(),quantitySearch.getSelectedItem(),
                        yearSearch.getSelectedItem());

                documentTableModel.setData(documents);
                tableScrollPanel.updateModel(tableModelMaker);

                if(documents.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "No documents was found for the selected filters.","No documents found",JOptionPane.INFORMATION_MESSAGE);
                }

            }catch (GetDocumentWithSpecificEventException err){
                JOptionPane.showMessageDialog(null, err.getMessage(), "",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        contentPanel.add(FilterLabelCenteredPanel, BorderLayout.NORTH);
        contentPanel.add(filterPanel, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(contentPanel);
        splitPane.setBottomComponent(tableScrollPanel);
        splitPane.setResizeWeight(0.70);
        splitPane.setDividerSize(5);

        add(splitPane,BorderLayout.CENTER);
    }

    private void onEventStepShown(){
        try {
            int itemId = itemSearch.getSelectedItem().getId();
            events = EventController.getEventsWithSpecificItem(itemId);
        }catch (GetEventsWithItemException e){
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
    private void onQuantityStepShown(){
        try {
            int idEventSelected = eventSearch.getSelectedItem().getId();
            int idItemSelected = itemSearch.getSelectedItem().getId();
            quantities = DocumentDetailsController.getQuantityItemWithSpecificEvent(idEventSelected, idItemSelected);
        }catch (GetQuantityItemWithSpecificEventException e){
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

    private void onYearsStepShown(){
        try{
            int idEventSelected = eventSearch.getSelectedItem().getId();
            years = EventController.getDatesEvents(idEventSelected);
        }catch (GetDatesEventsException e){
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

    private void onButtonStepShown()
    {
        clearPanelFilter();
        filters(yearSearch.getSelectedItem().toString());
    }

    private void setFilterLabel()
    {
        StringBuilder filterText = new StringBuilder();
        for (String filter : filters) {
            filterText.append(filter);
        }
        filterLabel.setText(filterText.toString());
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