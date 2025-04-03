package UI.Search;

import Controller.AppController;
import Controller.SearchController;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Event.GetEventsWithItemException;
import Exceptions.Search.GetQuantityItemWithSpecificEventException;
import Model.Event.Event;
import Model.Item.Item;
import UI.Components.GridBagLayoutHelper;
import UI.Components.SearchByLabelPanel;
import UI.Components.ComboBoxGen;

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

    public SearchDocumentWithEventForm(){

        setLayout(new BorderLayout());
        title = new JLabel("Search for a supplier order with specific filters");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // top, left, bottom, right

        filterLabel = new JLabel(String.join("", filters));

        filterLabelPanel = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
        filterLabelPanel.setBorder(border);
        filterLabelPanel.add(filterLabel);


        JPanel filterPanel = new JPanel(new GridBagLayout());
        GridBagLayoutHelper helper = new GridBagLayoutHelper(filterPanel);



        try{
            items = AppController.getAllItems();
        }catch (DatabaseConnectionFailedException e){
            System.out.println(e.getMessage());
        }

        currentPanelEvent = new JPanel(new BorderLayout());
        currentPanelQuantities = new JPanel(new BorderLayout());
        currentPanelYears = new JPanel(new BorderLayout());

        SearchByLabelPanel<Item> itemSearch = new SearchByLabelPanel<>(items,Item::getLabel);
        helper.addField(title);
        helper.addField(filterLabelPanel);
        helper.addField(itemSearch);
        helper.addField(currentPanelEvent);
        helper.addField(currentPanelQuantities);
        helper.addField(currentPanelYears);

        itemSearch.onSelectedItemChange(()->{
            Item itemSelected = itemSearch.getSelectedItem();
            if(itemSelected != null){
                idItemSelected = itemSelected.getId();
                currentIndexFilter = 0;
                filters(itemSelected.getLabel());
                try {
                    events = SearchController.getEventsWithSpecificItem(idItemSelected);
                    if(!events.isEmpty()){
                        currentPanelEvent.removeAll();
                        SearchByLabelPanel<Event> eventSearch = new SearchByLabelPanel<>(events,Event::getLabel);
                        currentPanelEvent.add(eventSearch, BorderLayout.CENTER);
                        currentPanelEvent.revalidate();
                        currentPanelEvent.repaint();

                        eventSearch.onSelectedItemChange(()->{
                            Event eventSelected = eventSearch.getSelectedItem();
                            if(eventSelected != null){
                                idEventSelected = eventSelected.getId();
                                currentIndexFilter = 1;
                                filters(eventSelected.getLabel());
                                try {
                                    quantities = SearchController.getQuantityItemWithSpecificEvent(eventSelected.getId());
                                    currentPanelQuantities.removeAll();
                                    SearchByLabelPanel<Float> quantitySearch = new SearchByLabelPanel<>(quantities, quantity -> Float.toString(quantity));
                                    currentPanelQuantities.add(quantitySearch, BorderLayout.CENTER);
                                    currentPanelQuantities.revalidate();
                                    currentPanelQuantities.repaint();
                                    quantitySearch.onSelectedItemChange(() -> {
                                        quantitySelected = quantitySearch.getSelectedItem();
                                        currentIndexFilter = 2;
                                        filters(quantitySelected.toString());
                                        if(quantitySelected != null){
                                            try{
                                                years = SearchController.getDatesEvents(idEventSelected);
                                                currentPanelYears.removeAll();
                                                SearchByLabelPanel<Integer> yearSearch = new SearchByLabelPanel<>(years, year -> Integer.toString(year));
                                                currentPanelYears.add(yearSearch, BorderLayout.CENTER);
                                                currentPanelYears.revalidate();
                                                currentPanelYears.repaint();

                                                yearSearch.onSelectedItemChange(() -> {
                                                    yearSelected = yearSearch.getSelectedItem();
                                                    currentIndexFilter = 3;
                                                    filters(yearSelected.toString());
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

    public void filters(String label) {
        String formattedLabel = label + " / ";

        if (currentIndexFilter < filters.size()) {
            filters.set(currentIndexFilter, formattedLabel);
        } else {
            filters.add(currentIndexFilter, formattedLabel);
        }
        filterLabel.setText("<html><span style='font-family: Arial; font-size: 9px; font-weight: none; color: #136F63;'>"
                + String.join(" ", filters) + "</span></html>");
    }
}