package UI.Search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.Search.SearchItemException;
import Exceptions.Vat.UnkownVatCodeException;
import Exceptions.Vat.WrongVatCodeException;
import UI.Components.JDualSliderPanel;
import UI.Components.GridBagLayoutHelper;

import Model.Item.Item;

import Controller.SearchController;
import UI.Components.JEnhancedTextField;


public class SearchItemForm extends JPanel
{
    //private final SearchController searchController = new SearchController();

    public SearchItemForm()
    {
        int[] minMaxItem;
        try
        {
            minMaxItem = SearchController.getMinMaxItemQuantityAndPrice();
        }
        catch (GetMinMaxItemQuantityAndPriceException | DatabaseConnectionFailedException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            minMaxItem = new int[]{0, 100, 0, 100};
        }

        // add a title
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Search Items");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel searchForm = new JPanel(new GridBagLayout());
        GridBagLayoutHelper gridSearchForm = new GridBagLayoutHelper(searchForm);

        // TVA Code field
        JEnhancedTextField tvaCodeField = new JEnhancedTextField();
        tvaCodeField.setPlaceholder("TVA Code");
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
        table.setModel(new ItemTableModel(new ArrayList<>()));
        add(new JScrollPane(table), BorderLayout.SOUTH);

        searchButton.addActionListener(e ->
        {
            searchItem(tvaCodeField, nbItemInPackaging, priceRange, table);
        });

        // add event lister on enter key
        tvaCodeField.addActionListener((ActionEvent e) -> searchButton.doClick());



    }

    private void searchItem(JTextField tvaCodeField, JDualSliderPanel nbItemInPackaging, JDualSliderPanel priceRange, JTable table)
    {
        // get data from fields
        String tvaCode = tvaCodeField.getText();
        int minItem = nbItemInPackaging.getCurrentMin();
        int maxItem = nbItemInPackaging.getCurrentMax();
        int minPrice = priceRange.getCurrentMin();
        int maxPrice = priceRange.getCurrentMax();

        try
        {
            ArrayList<Item> items = SearchController.searchItem(tvaCode, minItem, maxItem, minPrice, maxPrice);

            // Update table
            table.setModel(new ItemTableModel(items));
            revalidate();
        }
        catch (WrongVatCodeException | DatabaseConnectionFailedException | UnkownVatCodeException |
               SearchItemException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
