package UI.BusinessTasks;

import Controller.Document.DocumentController;
import Controller.Item.ItemController;
import Model.Document.Document;
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

            if (restockItem == null)
            {
                JOptionPane.showMessageDialog(this, "Please select an item and a date", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }



        });

        layoutHelper.addField("Select an item", itemSearch);
        layoutHelper.addField("Restock date", dateField);
        layoutHelper.addField(restockButton);

        add(layoutHelper);
    }

    private RestockItem calculateRestockQuantityAndDate()
    {
        if(itemSearch.getSelectedItem() == null) return null;
        //if(dateField.getDate() == null) return null;

        ArrayList<Document> documents = DocumentController.getAllCurrentCommandsForAnItem(itemSearch.getSelectedItem());

         // log all documents
        for (Document document : documents)
        {
            System.out.println(document);
        }

        return null;
    }

    public record RestockItem(int quantity, String date)
    {
    }
}
