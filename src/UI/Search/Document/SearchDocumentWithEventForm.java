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
    private int idItemSelected;
    private int idEventSelected;
    private Float quantitySelected;
    private Integer yearSelected;
    private JPanel currentPanelEvent;
    private JPanel currentPanelQuantities;
    private JPanel currentPanelYears;
    private JPanel panelSearchButton;

    private JButton searchButton;

    // @todo : modifier le système de panel et searchByLabelPanel pour les filtres
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

        currentPanelEvent = new JPanel(new BorderLayout());
        currentPanelQuantities = new JPanel(new BorderLayout());
        currentPanelYears = new JPanel(new BorderLayout());

        currentPanels.add(currentPanelEvent);
        currentPanels.add(currentPanelQuantities);
        currentPanels.add(currentPanelYears);



        SearchByLabelPanel<Item> itemSearch = new SearchByLabelPanel<>(items,Item::getLabel);
        filterPanel.addField(title);
        filterPanel.addField(filterLabelPanel);
        filterPanel.addField(itemSearch);
        filterPanel.addField(currentPanelEvent);
        filterPanel.addField(currentPanelQuantities);
        filterPanel.addField(currentPanelYears);
        filterPanel.addField(searchButton);

        System.out.println(currentPanels.size());

        itemSearch.onSelectedItemChange(itemChanged->{
            Item itemSelected = itemSearch.getSelectedItem();
            if(itemSelected != null){
                currentIndexFilter = 0;
                idItemSelected = itemSelected.getId();
                filters(itemSelected.getLabel());

                clearPanelFilter();

                try {
                    events = SearchController.getEventsWithSpecificItem(idItemSelected);
                    if(!events.isEmpty()){
                        currentPanelEvent.removeAll();
                        SearchByLabelPanel<Event> eventSearch = new SearchByLabelPanel<>(events,Event::getLabel);
                        currentPanelEvent.add(eventSearch, BorderLayout.CENTER);
                        currentPanelEvent.revalidate();
                        currentPanelEvent.repaint();

                        eventSearch.onSelectedItemChange(eventChanged ->{
                            Event eventSelected = eventSearch.getSelectedItem();
                            if(eventSelected != null){
                                currentIndexFilter = 1;
                                idEventSelected = eventSelected.getId();
                                clearPanelFilter();
                                filters(eventSelected.getLabel());
                                try {
                                    quantities = SearchController.getQuantityItemWithSpecificEvent(eventSelected.getId());
                                    currentPanelQuantities.removeAll();
                                    SearchByLabelPanel<Float> quantitySearch = new SearchByLabelPanel<>(quantities, quantity -> Float.toString(quantity));
                                    currentPanelQuantities.add(quantitySearch, BorderLayout.CENTER);
                                    currentPanelQuantities.revalidate();
                                    currentPanelQuantities.repaint();
                                    quantitySearch.onSelectedItemChange(quantityChanged -> {
                                        quantitySelected = quantitySearch.getSelectedItem();
                                        if(quantitySelected != null){
                                            currentIndexFilter = 2;
                                            filters(quantitySelected.toString());
                                            clearPanelFilter();
                                            try{
                                                years = SearchController.getDatesEvents(idEventSelected);
                                                currentPanelYears.removeAll();
                                                SearchByLabelPanel<Integer> yearSearch = new SearchByLabelPanel<>(years, year -> Integer.toString(year));
                                                currentPanelYears.add(yearSearch, BorderLayout.CENTER);
                                                currentPanelYears.revalidate();
                                                currentPanelYears.repaint();

                                                yearSearch.onSelectedItemChange(a -> {
                                                    yearSelected = yearSearch.getSelectedItem();
                                                    currentIndexFilter = 3;
                                                    filters(yearSelected.toString());
                                                    if(yearSelected != null){
                                                        searchButton.setVisible(true);
                                                    }
                                                });
                                            }catch (DatabaseConnectionFailedException e){
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                    });
                                }catch (DatabaseConnectionFailedException | GetQuantityItemWithSpecificEventException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                        });

                    }
                }catch (DatabaseConnectionFailedException | GetEventsWithItemException e){
                    System.out.println(e.getMessage());
                }

                System.out.println(itemSelected.getLabel());
            }
        });

        add(filterPanel, BorderLayout.CENTER);

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