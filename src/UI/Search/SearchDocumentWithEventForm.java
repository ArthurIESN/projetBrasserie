package UI.Search;

import Controller.AppController;
import Controller.SearchController;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
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
    private YearComboBox yearsComboBox;
    //private ItemComboBox itemComboBox;

    private SearchByLabelPanel yearSearch;
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

        //yearSearch = new SearchByLabelPanel<>(years,Event::getStartDate)
        yearsComboBox = new YearComboBox(years);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //filterPanel.add(yearsComboBox);

        try{
            items = AppController.getAllItems();
        }catch (DatabaseConnectionFailedException e){
            System.out.println(e.getMessage());
        }

        SearchByLabelPanel<Item> itemSearch = new SearchByLabelPanel<>(items,Item::getLabel);



        //itemComboBox = new ItemComboBox(items);
        //filterPanel.add(itemComboBox);

        filterPanel.add(itemSearch);
        filterPanel.add(yearsComboBox);
        add(filterPanel, BorderLayout.CENTER);

       // System.out.println(itemComboBox.getSelectedItemId());


    }


}