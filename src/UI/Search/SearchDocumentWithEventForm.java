package UI.Search;

import Controller.AppController;
import Controller.SearchController;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Model.Item.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchDocumentWithEventForm extends JPanel {
    private JLabel title;
    private JComboBox<Integer> yearsComboBox;
    private List<Integer> years = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    public SearchDocumentWithEventForm(){

        setLayout(new BorderLayout());
        title = new JLabel("Search 2");

        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        add(title, BorderLayout.NORTH);


        try{
            years = SearchController.getInstance().getDatesEvents();
        }catch (DatabaseConnectionFailedException e){
            System.out.println(e.getMessage());
        }


        Integer[] yearsArray = years.toArray(new Integer[0]);
        yearsComboBox = new JComboBox<>(yearsArray);


        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        try{
            items = AppController.getInstance().getAllItems();
        }catch (DatabaseConnectionFailedException e){
            System.out.println(e.getMessage());
        }

        System.out.println(items);

        filterPanel.add(yearsComboBox);
        add(filterPanel, BorderLayout.CENTER);
    }
}