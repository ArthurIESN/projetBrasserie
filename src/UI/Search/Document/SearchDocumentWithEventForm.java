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
    private List<JPanel> currentPanels = new ArrayList<>();
    private List<String> filters = new ArrayList<>();
    private List<Integer> years = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    private List<Float> quantities = new ArrayList<>();
    private SearchByLabelPanel<Item> itemSearch;
    private SearchByLabelPanel<Event> eventSearch;
    private SearchByLabelPanel<Float> quantitySearch;
    private SearchByLabelPanel<Integer> yearSearch;
    private int idItemSelected;
    private int idEventSelected;
    private Float quantitySelected;
    private Integer yearSelected;
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


        StepByStepManager stepByStepManager = new StepByStepManager(stepsList);

        itemSearch.onSelectedItemChange(itemChanged -> {
            Item itemSelected = itemSearch.getSelectedItem();
            idItemSelected = itemSelected.getId();
            currentIndexFilter = 0;
            stepByStepManager.completeStep(0);
        });

        eventSearch.onSelectedItemChange(eventChanged -> {
            Event eventSelected = eventSearch.getSelectedItem();
            idEventSelected = eventSelected.getId();
            currentIndexFilter = 1;
            stepByStepManager.completeStep(1);
        });

        quantitySearch.onSelectedItemChange(quantityChanged -> {
            quantitySelected = quantitySearch.getSelectedItem();
            currentIndexFilter = 2;
            stepByStepManager.completeStep(2);
        });

        yearSearch.onSelectedItemChange(yearChanged -> {
            yearSelected = yearSearch.getSelectedItem();
            System.out.println(
                    yearSelected
            );
            currentIndexFilter = 3;
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
            events = SearchController.getEventsWithSpecificItem(idItemSelected);
        }catch (DatabaseConnectionFailedException | GetEventsWithItemException e){
            System.out.println(e.getMessage());
        }
        if(!events.isEmpty()){
            eventSearch.setSelectedItem(null);
            eventSearch.setData(events);
        }else {
            eventSearch.setVisible(false);
        }
    }
    private void functionCalledWhenStepQuantitiesIsShown(){
        try {
            quantities = SearchController.getQuantityItemWithSpecificEvent(idEventSelected);
        }catch (DatabaseConnectionFailedException | GetQuantityItemWithSpecificEventException e){
            System.out.println(e.getMessage());
        }
        if(!quantities.isEmpty()){
            quantitySearch.setSelectedItem(null);
            quantitySearch.setData(quantities);

        }else {
            quantitySearch.setVisible(false);
        }
    }

    private void functionCalledWhenStepYearsIsShown(){
        try{
            years = SearchController.getDatesEvents(idEventSelected);
        }catch (DatabaseConnectionFailedException e){
            System.out.println(e.getMessage());
        }
        if(!years.isEmpty()){
            yearSearch.setSelectedItem(null);
            yearSearch.setData(years);
        }else {
            yearSearch.setVisible(false);
        }
    }

    private void functionCalledWhenStepButtonIsShown(){
        searchButton.setVisible(true);
    }

    private void setFilterLabel(){
        filterLabel.setText("<html><span style='font-family: Arial; font-size: 9px; font-weight: none; color: #136F63;'>"
                + String.join(" ", filters) + "</span></html>");
    }

    private void filters(String label) {
        String formattedLabel = label + " / ";

        if (currentIndexFilter < filters.size()) {
            filters.set(currentIndexFilter, formattedLabel);
        } else {
            filters.add(currentIndexFilter, formattedLabel);
        }

        setFilterLabel();
    }

    private void clearPanelFilter(){
       for (int i = currentIndexFilter; i < currentPanels.size(); i++) {
            currentPanels.get(i).removeAll();
            if(i == currentPanels.size() - 1){
                yearSelected = null;
            }
        }

       if (yearSelected == null) {
            searchButton.setVisible(false);
       }

       for(int i = currentIndexFilter + 1; i < filters.size(); i++){
           filters.remove(i);
           i--;
       }
       setFilterLabel();

    }
}