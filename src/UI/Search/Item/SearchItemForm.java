package UI.Search.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Controller.Item.ItemController;
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

import UI.Components.EnhancedTable.JEnhancedTableScrollPanel;
import UI.Components.EnhancedTable.TableModelMaker;
import UI.Components.Fields.ComboBoxPanel;
import UI.Components.Fields.JDualSliderPanel;
import UI.Models.ItemEnhancedTableModel;
import UI.Models.PackagingEnhancedTableModel;
import Utils.Utils;


public class SearchItemForm extends JPanel
{
    private final JEnhancedTableScrollPanel tableScrollPanel;
    private final TableModelMaker tableModelMaker;
    private final ItemEnhancedTableModel itemTableModel;
    private final PackagingEnhancedTableModel packagingEnhancedTableModel;

    private final JDualSliderPanel itemQuantity;
    private final JDualSliderPanel itemPrice;
    private final ComboBoxPanel<Vat> searchVat;


    public SearchItemForm()
    {
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

        searchVat.onSelectedItemChange(vat ->
                setQuantityAndPrice());


        // Item Stock Quantity Field
        itemQuantity = new JDualSliderPanel(0, 100, 500, 50);
        gridSearchForm.addField("Item Stock Quantity", itemQuantity);

        // Item Price Field
        itemPrice = new JDualSliderPanel(0, 100, 500, 50);
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

        if(vats.size() > 1)
        {
            searchVat.setSelectedItem(vats.get(1));
        }
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
            ArrayList<Item> items = ItemController.searchItem(tvaCode, minItem, maxItem, minPrice, maxPrice);
            ArrayList<Packaging> packages = Utils.transformData(items, Item::getPackaging);

            itemTableModel.setData(items);
            packagingEnhancedTableModel.setData(packages);

            tableScrollPanel.updateModel(tableModelMaker);


            if(items.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "No item found", "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        }
        catch (WrongVatCodeException | SearchItemException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void setQuantityAndPrice()
    {
        int[] minMaxItem;
        try
        {
            minMaxItem = ItemController.getMinMaxItemQuantityAndPrice(searchVat.getSelectedItem());

            itemQuantity.setMinMax(minMaxItem[0], minMaxItem[1]);
            itemPrice.setMinMax(minMaxItem[2], minMaxItem[3]);
        }
        catch (GetMinMaxItemQuantityAndPriceException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
