package UI.BusinessTasks;

import Controller.Item.ItemController;
import Model.Item.Item;
import UI.Components.Fields.JDateField;
import UI.Components.Fields.SearchListPanel;
import UI.Components.GridBagLayoutHelper;

import javax.swing.*;
import java.util.ArrayList;

public class RestockItemPanel extends JPanel
{
    private SearchListPanel<Item> itemSearch;
    private JDateField dateField;
    private JButton restockButton;
    private GridBagLayoutHelper layoutHelper;

    public RestockItemPanel()
    {
        ArrayList<Item> items = new ArrayList<>();

        try
        {
            items = ItemController.getAllItems();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        layoutHelper = new GridBagLayoutHelper();

        itemSearch = new SearchListPanel<>(items, Item::getLabel);
        itemSearch.getSearchField().setPlaceholder("Search for an item");

        dateField = new JDateField();
        dateField.setPlaceholder("Enter restock date");

        restockButton = new JButton("Restock");

        restockButton.addActionListener(e ->
        {
            RestockItem restockItem = calculateRestockQuantityAndDate();

            // display informations
        });

        layoutHelper.addField("Select an item", itemSearch);
        layoutHelper.addField("Restock date", dateField);

        add(layoutHelper);
    }

    private RestockItem calculateRestockQuantityAndDate()
    {
        if(itemSearch.getSelectedItem() == null) return null;
        if(dateField.getDate() == null) return null;

        return null;
    }

    public record RestockItem(int quantity, String date)
    {
    }
}
