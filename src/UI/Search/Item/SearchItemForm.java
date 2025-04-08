package UI.Search.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.Search.SearchItemException;
import Exceptions.Vat.UnkownVatCodeException;
import Exceptions.Vat.WrongVatCodeException;
import Model.Packaging.Packaging;
import Model.Vat.Vat;
import UI.Components.*;

import Model.Item.Item;

import Controller.SearchController;
import UI.Components.EnhancedTable.JEnhancedTableScrollPanel;
import UI.Components.EnhancedTable.TableModelMaker;
import UI.Components.Fields.JDualSliderPanel;
import UI.Components.Fields.JEnhancedTextField;
import UI.Models.ItemEnhancedTableModel;
import UI.Models.PackagingEnhancedTableModel;
import UI.Models.VatEnhancedTableModel;
import Utils.Utils;


public class SearchItemForm extends JPanel
{
    private JEnhancedTableScrollPanel tableScrollPanel;
    private TableModelMaker tableModelMaker;
    private ItemEnhancedTableModel itemTableModel;
    private PackagingEnhancedTableModel packagingEnhancedTableModel;
    private VatEnhancedTableModel vatEnhancedTableModel;


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

        //JPanel searchForm = new JPanel(new GridBagLayout());
        GridBagLayoutHelper gridSearchForm = new GridBagLayoutHelper();

        // TVA Code field
        JEnhancedTextField tvaCodeField = new JEnhancedTextField();
        tvaCodeField.setPlaceholder("VAT Code");
        gridSearchForm.addRightField(tvaCodeField);

        // Item Stock Quantity Field
        JDualSliderPanel nbItemInPackaging = new JDualSliderPanel(minMaxItem[0], minMaxItem[1], 300, 50);
        gridSearchForm.addField("Item Stock Quantity", nbItemInPackaging);

        // Item Price Field
        JDualSliderPanel priceRange = new JDualSliderPanel(minMaxItem[2], minMaxItem[3], 300, 50);
        gridSearchForm.addField("Item Price", priceRange);

        JButton searchButton = new JButton("Search");
        gridSearchForm.addField(searchButton);

        add(gridSearchForm, BorderLayout.CENTER);


        // Empty table
        tableModelMaker = new TableModelMaker();
        itemTableModel = new ItemEnhancedTableModel(new ArrayList<>());
        packagingEnhancedTableModel = new PackagingEnhancedTableModel(new ArrayList<>());
        //vatEnhancedTableModel = new VatEnhancedTableModel(new ArrayList<>());

        tableModelMaker.addTableModel(itemTableModel);
        tableModelMaker.addTableModel(packagingEnhancedTableModel);
       // tableModelMaker.addTableModel(vatEnhancedTableModel);

        tableScrollPanel = new JEnhancedTableScrollPanel(tableModelMaker, this);
        tableModelMaker.setTable(tableScrollPanel);
        add(tableScrollPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e ->
        {
            searchItem(tvaCodeField, nbItemInPackaging, priceRange, tableScrollPanel);
        });

        // add event lister on enter key
        tvaCodeField.addActionListener((ActionEvent e) -> searchButton.doClick());
    }

    private void searchItem(JTextField tvaCodeField, JDualSliderPanel nbItemInPackaging, JDualSliderPanel priceRange, JEnhancedTableScrollPanel table)
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
            ArrayList<Packaging> packagings = Utils.transformData(items, Item::getPackaging);
            //ArrayList<Vat> vatCodes = Utils.transformData(items, Item::getVat);

            itemTableModel.setData(items);
            packagingEnhancedTableModel.setData(packagings);
            //vatEnhancedTableModel.setData(vatCodes);

            tableScrollPanel.updateModel(tableModelMaker);


            if(items.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "No item found", "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        }
        catch (WrongVatCodeException | DatabaseConnectionFailedException | UnkownVatCodeException |
               SearchItemException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
