package UI.BusinessTasks;

import Controller.BusinessTasks.RestockItemController;
import Controller.Date.DateController;
import Controller.Event.EventController;
import Controller.Item.ItemController;

import Exceptions.Access.UnauthorizedAccessException;
import Exceptions.Event.GetEventsBeforeDateException;

import Exceptions.Tasks.RestockItem.RestockQuantityAndDateException;
import Model.Event.Event;
import Model.Item.Item;
import UI.Components.Fields.JDateField;
import UI.Components.Fields.SearchListPanel;
import UI.Components.GridBagLayoutHelper;
import UI.Threads.LoadingThread;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public class RestockItemPanel extends JPanel
{
    private final SearchListPanel<Item> itemSearch;
    private final JDateField dateField;

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

        GridBagLayoutHelper layoutHelper = new GridBagLayoutHelper();

        itemSearch = new SearchListPanel<>(items, Item::getLabel);
        itemSearch.getSearchField().setPlaceholder("Search for an item");

        dateField = new JDateField();
        dateField.setPlaceholder("Enter restock date");
        dateField.setMinDate(new java.util.Date());

        JButton restockButton = new JButton("Restock");

        restockButton.addActionListener(e ->
        {
            int randomWait = (int) (Math.random() * 10000);
            LoadingThread loadingThread = new LoadingThread("Restock Item", "Calculating restock quantities...", randomWait);

            loadingThread.start();

            ArrayList<Item.RestockItem> restockItem;
            try
            {
                restockItem = calculateRestockQuantityAndDate();
            }
            catch (RestockQuantityAndDateException | UnauthorizedAccessException ex)
            {
                loadingThread.stopLoading();
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (restockItem == null)
            {
                System.out.println("Restock item is null");
                loadingThread.stopLoading();
                return;
            }


            loadingThread.onLoadingComplete(() ->
            {
                for (Item.RestockItem item : restockItem)
                {
                    createRestockItemUIComponent(item);
                }

                revalidate();
                repaint();
            });


        });

        layoutHelper.addField("Select an item", itemSearch);
        layoutHelper.addField("Restock date", dateField);
        layoutHelper.addField(restockButton);

        add(layoutHelper);
    }

    private ArrayList<Item.RestockItem> calculateRestockQuantityAndDate() throws RestockQuantityAndDateException, UnauthorizedAccessException
    {
        if(itemSearch.getSelectedItem() == null) return null;
        if(dateField.getDate() == null) return null;

        return RestockItemController.calculateRestockQuantityAndDate(itemSearch.getSelectedItem(), dateField.getDate());
    }

    private void createRestockItemUIComponent(Item.RestockItem restockItem) {
        JPanel restockItemPanel = new JPanel();
        restockItemPanel.setLayout(new BoxLayout(restockItemPanel, BoxLayout.Y_AXIS));

        JLabel itemLabel = new JLabel("Quantity: " + restockItem.getQuantity());
        JLabel dateLabel = new JLabel("Date: " + DateController.getDateString(restockItem.getDate()));

        restockItemPanel.add(itemLabel);
        restockItemPanel.add(dateLabel);

        if(restockItem.getEvent() != null)
        {
            JLabel eventLabel = new JLabel("Event: " + restockItem.getEvent().getLabel());
            restockItemPanel.add(eventLabel);
        }

        add(restockItemPanel);
    }
}
