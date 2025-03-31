package UI.Search;

import Controller.AppController;
import Controller.SearchController;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Event.GetEventsWithItemException;
import Model.Event.Event;
import Model.Item.Item;
import UI.Components.SearchByLabelPanel;
import UI.Components.YearComboBox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SearchDocumentWithEventForm extends JPanel {
    private JLabel title;
    private List<Integer> years = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    private YearComboBox yearsComboBox;
    private int idItemSelected;

    public SearchDocumentWithEventForm(){

        setLayout(new BorderLayout());
        title = new JLabel("Search 2");

        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        add(title, BorderLayout.NORTH);

        try{
            years = SearchController.getDatesEvents();
        }catch (DatabaseConnectionFailedException e){
            System.out.println(e.getMessage());
        }

        yearsComboBox = new YearComboBox(years);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


        try{
            items = AppController.getAllItems();
        }catch (DatabaseConnectionFailedException e){
            System.out.println(e.getMessage());
        }

        SearchByLabelPanel<Item> itemSearch = new SearchByLabelPanel<>(items,Item::getLabel);

        itemSearch.onSelectedItemChange(()->{
            Item itemSelected = itemSearch.getSelectedItem();
            if(itemSelected != null){
                idItemSelected = itemSelected.getId();
                try {
                    events = SearchController.getEventsWithSpecificItem(idItemSelected);
                    if(!events.isEmpty()){
                        SearchByLabelPanel<Event> eventSearch = new SearchByLabelPanel<>(events,"Search an event",Event::getLabel);
                        filterPanel.add(eventSearch);
                        filterPanel.revalidate();
                        filterPanel.repaint();
                    }
                }catch (DatabaseConnectionFailedException | GetEventsWithItemException e){
                    System.out.println(e.getMessage());
                }

                System.out.println(itemSelected.getLabel());
            }
        });


        filterPanel.add(itemSearch);

        filterPanel.add(yearsComboBox);

        add(filterPanel, BorderLayout.CENTER);


    }


}