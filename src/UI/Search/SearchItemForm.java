package UI.Search;

import javax.swing.*;

import UI.Components.JDualSliderPanel;
import UI.Components.GridBagLayoutHelper;

import Model.Item.Item;
import Model.Item.ItemTable;

import DataAccess.SearchItemDBAccess; // JUST FOR TESTING @todo replace with the corresponding controller

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SearchItemForm extends JPanel
{
    public SearchItemForm()
    {
        //@todo : just for testing, move this to the corresponding controller
        SearchItemDBAccess searchItemDBAccess = new SearchItemDBAccess();
        int[] minMaxItem = searchItemDBAccess.getMinMaxItemQuantityAndPrice();

        // add a title
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Search Items");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel searchForm = new JPanel(new GridBagLayout());
        GridBagLayoutHelper gridSearchForm = new GridBagLayoutHelper(searchForm);

        // TVA Code field
        JTextField tvaCodeField = new JTextField();
        gridSearchForm.addField("TVA Code", tvaCodeField);

        // Item Stock Quantity Field
        JDualSliderPanel nbItemInPackaging = new JDualSliderPanel(minMaxItem[0], minMaxItem[1], 300, 50);
        gridSearchForm.addField("Item Stock Quantity", nbItemInPackaging);

        // Item Price Field
        JDualSliderPanel priceRange = new JDualSliderPanel(minMaxItem[2], minMaxItem[3], 300, 50);
        gridSearchForm.addField("Item Price", priceRange);

        add(searchForm, BorderLayout.CENTER);

        // add a button to search
        JButton searchButton = new JButton("Search");
        add(searchButton, BorderLayout.EAST);

        // Empty table
        JTable table = new JTable();
        table.setModel(new ItemTable(new ArrayList<>()));
        add(new JScrollPane(table), BorderLayout.SOUTH);

        searchButton.addActionListener(e ->
        {
            searchItem(searchItemDBAccess, tvaCodeField, nbItemInPackaging, priceRange, table);
        });

        // add event lister on enter key
        tvaCodeField.addActionListener((ActionEvent e) -> searchButton.doClick());



    }

    private void searchItem(SearchItemDBAccess searchItemDBAccess, JTextField tvaCodeField, JDualSliderPanel nbItemInPackaging, JDualSliderPanel priceRange, JTable table)
    {
        String tvaCode = tvaCodeField.getText();
        int minItem = nbItemInPackaging.getCurrentMin();
        int maxItem = nbItemInPackaging.getCurrentMax();
        int minPrice = priceRange.getCurrentMin();
        int maxPrice = priceRange.getCurrentMax();

        // call the searchItem method from the SearchItemDBAccess class
        ArrayList<Item> Items = searchItemDBAccess.searchItem(tvaCode, minItem, maxItem, minPrice, maxPrice);

        // Update table
        table.setModel(new ItemTable(Items));
        revalidate();
    }
}
