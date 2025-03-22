package UI.Search;

import javax.swing.*;

import UI.Components.JDualSliderPanel;
import UI.Components.GridBagLayoutHelper;

import Model.Item.Item;
import Model.Item.ItemTable;

import DataAccess.SearchItemDBAccess; // JUST FOR TESTING @todo replace with the corresponding controller

import java.awt.*;
import java.util.ArrayList;

public class SearchItemForm extends JPanel
{
    public SearchItemForm()
    {
        // add a title
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Search Items");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel searchForm = new JPanel(new GridBagLayout());
        GridBagLayoutHelper gridSearchForm = new GridBagLayoutHelper(searchForm);

        // TVA Code field
        JTextField tvaCodeField = new JTextField();
        gridSearchForm.addComponent("TVA Code", tvaCodeField);

        // NbItemInStock field
        //@todo (get the max from the database ?)
        JDualSliderPanel nbItemInPackaging = new JDualSliderPanel(1, 100 , 1, 100, 300, 50);
        gridSearchForm.addComponent("Number of Items in Stock", nbItemInPackaging);

        // Range price field
        JDualSliderPanel priceRange = new JDualSliderPanel(0, 150, 0, 150, 300, 50);
        gridSearchForm.addComponent("Price Range", priceRange);

        add(searchForm, BorderLayout.CENTER);


        // add a button to search
        JButton searchButton = new JButton("Search");
        add(searchButton, BorderLayout.EAST);

        JTable table = new JTable();
        table.setModel(new ItemTable(new ArrayList<>()));
        add(new JScrollPane(table), BorderLayout.SOUTH);

        searchButton.addActionListener(e ->
        {
            String tvaCode = tvaCodeField.getText();
            int minItem = nbItemInPackaging.getCurrentMin();
            int maxItem = nbItemInPackaging.getCurrentMax();
            int minPrice = priceRange.getCurrentMin();
            int maxPrice = priceRange.getCurrentMax();

            // call the searchItem method from the SearchItemDBAccess class
            SearchItemDBAccess searchItemDBAccess = new SearchItemDBAccess();
            ArrayList<Item> Items = searchItemDBAccess.searchItem(tvaCode, minItem, maxItem, minPrice, maxPrice);

            // Update table
            table.setModel(new ItemTable(Items));
            revalidate();
        });
    }
}
