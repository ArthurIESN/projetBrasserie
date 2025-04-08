package UI.Search.Document;

import Controller.AppController;
import Controller.SearchController;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Event.GetEventsWithItemException;
import Exceptions.Search.GetQuantityItemWithSpecificEventException;
import Model.Event.Event;
import Model.Item.Item;
import UI.Components.GridBagLayoutHelper;
import UI.Components.Fields.SearchByLabelPanel;
import UI.Components.StepByStepManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SearchDocumentWithEventForm extends JPanel {
    private JLabel title;
    private JPanel filterLabelPanel;
    private JLabel filterLabel;
    private int currentIndexFilter;
    private StepByStepManager stepByStepManager;
    private List<String> filters = new ArrayList<>();
    private List<Integer> years = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    private List<Float> quantities = new ArrayList<>();
    private SearchByLabelPanel<Item> itemSearch;
    private SearchByLabelPanel<Event> eventSearch;
    private SearchByLabelPanel<Float> quantitySearch;
    private SearchByLabelPanel<Integer> yearSearch;
    private JPanel panelSearchButton;
    private JButton searchButton;

    public SearchDocumentWithEventForm(){

        setLayout(new BorderLayout());
        title = new JLabel("Search for a supplier order with specific filters");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // top, left, bottom, right

        filterLabel = new JLabel(String.join("", filters));

        panelSearchButton = new JPanel();
        searchButton = new JButton("Search");
        searchButton.setVisible(false);

        filterLabelPanel = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
        filterLabelPanel.setBorder(border);
        filterLabelPanel.add(filterLabel);


        GridBagLayoutHelper filterPanel = new GridBagLayoutHelper();

        try{
            items = AppController.getAllItems();
        }catch (DatabaseConnectionFailedException e){
            System.out.println(e.getMessage());
        }

        itemSearch = new SearchByLabelPanel<>(items, Item::getLabel);
        eventSearch = new SearchByLabelPanel<>(new ArrayList<>(), Event::getLabel);
        quantitySearch = new SearchByLabelPanel<>(new ArrayList<>(), quantity -> Float.toString(quantity));
        yearSearch = new SearchByLabelPanel<>(new ArrayList<>(), year -> Integer.toString(year));


        Component[] stepsList = new Component[] {itemSearch,eventSearch,quantitySearch,yearSearch,searchButton};


        filterPanel.addField(title);
        filterPanel.addField(filterLabelPanel);

        filterPanel.addField(itemSearch);
        filterPanel.addField(eventSearch);
        filterPanel.addField(quantitySearch);
        filterPanel.addField(yearSearch);
        filterPanel.addField(searchButton);


        stepByStepManager = new StepByStepManager(stepsList);

        itemSearch.onSelectedItemChange(itemChanged ->
        {
            stepByStepManager.completeStep(0);
        });

        eventSearch.onSelectedItemChange(eventChanged -> {
            stepByStepManager.completeStep(1);
        });

        quantitySearch.onSelectedItemChange(quantityChanged -> {
            stepByStepManager.completeStep(2);
        });

        yearSearch.onSelectedItemChange(yearChanged -> {
            stepByStepManager.completeStep(3);
        });


        stepByStepManager.onStepShown(1, this::functionCalledWhenStepEventIsShown);
        stepByStepManager.onStepShown(2, this::functionCalledWhenStepQuantitiesIsShown);
        stepByStepManager.onStepShown(3, this::functionCalledWhenStepYearsIsShown);
        stepByStepManager.onStepShown(4, this::functionCalledWhenStepButtonIsShown);


        add(filterPanel, BorderLayout.CENTER);

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
            stepByStepManager.stop();
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
            stepByStepManager.stop();
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
            stepByStepManager.stop();
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

        if (stepByStepManager.getCurrentStep() < filters.size())
        {
            filters.set(stepByStepManager.getCurrentStep(), formattedLabel);
        } else {
            filters.add(formattedLabel);
        }

        setFilterLabel();
    }

    private void clearPanelFilter()
    {
        for(int i = stepByStepManager.getCurrentStep() - 1;  i < filters.size(); i++)
        {
            filters.remove(i);
            i--;
        }

        setFilterLabel();
    }
}