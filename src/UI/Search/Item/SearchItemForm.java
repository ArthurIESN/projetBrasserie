package UI.Search.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Controller.VAT.VATController;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.Search.SearchItemException;
import Exceptions.Vat.GetAllVatsException;
import Exceptions.Vat.UnkownVatCodeException;
import Exceptions.Vat.WrongVatCodeException;
import Model.Packaging.Packaging;
import Model.Vat.Vat;
import UI.Components.*;

import Model.Item.Item;

import Controller.SearchController;
import UI.Components.EnhancedTable.JEnhancedTableScrollPanel;
import UI.Components.EnhancedTable.TableModelMaker;
import UI.Components.Fields.ComboBoxPanel;
import UI.Components.Fields.JDualSliderPanel;
import UI.Models.ItemEnhancedTableModel;
import UI.Models.PackagingEnhancedTableModel;
import Utils.Utils;


public class SearchItemForm extends JPanel
{
    private JEnhancedTableScrollPanel tableScrollPanel;
    private TableModelMaker tableModelMaker;
    private ItemEnhancedTableModel itemTableModel;
    private PackagingEnhancedTableModel packagingEnhancedTableModel;

    private JDualSliderPanel itemQuantity;
    private JDualSliderPanel itemPrice;
    private ComboBoxPanel<Vat> searchVat;


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

        ArrayList<Vat> vats = new ArrayList<>();
        try
        {
            vats = VATController.getAllVats();
        } catch (GetAllVatsException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // add a title
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Search Items");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        //JPanel searchForm = new JPanel(new GridBagLayout());
        GridBagLayoutHelper gridSearchForm = new GridBagLayoutHelper();

        // TVA Code field
        searchVat = new ComboBoxPanel<>(vats, Vat::getCode);
        gridSearchForm.addField("VAT Code", searchVat);


        // Item Stock Quantity Field
        itemQuantity = new JDualSliderPanel(minMaxItem[0], minMaxItem[1], 500, 50);
        gridSearchForm.addField("Item Stock Quantity", itemQuantity);

        // Item Price Field
        itemPrice = new JDualSliderPanel(minMaxItem[2], minMaxItem[3], 500, 50);
        gridSearchForm.addField("Item Price", itemPrice);

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

        searchButton.addActionListener(e -> searchItem());
    }

    private void searchItem()
    {
        Vat selectedVat = searchVat.getSelectedItem();
        if(selectedVat == null)
        {
            JOptionPane.showMessageDialog(null, "Please select a VAT code", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tvaCode = selectedVat.getCode();
        int minItem = itemQuantity.getCurrentMin();
        int maxItem = itemQuantity.getCurrentMax();
        int minPrice = itemPrice.getCurrentMin();
        int maxPrice = itemPrice.getCurrentMax();

        try
        {
            ArrayList<Item> items = SearchController.searchItem(tvaCode, minItem, maxItem, minPrice, maxPrice);
            ArrayList<Packaging> packagings = Utils.transformData(items, Item::getPackaging);

            itemTableModel.setData(items);
            packagingEnhancedTableModel.setData(packagings);

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
