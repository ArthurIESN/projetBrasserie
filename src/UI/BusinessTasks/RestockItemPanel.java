package UI.BusinessTasks;

import Controller.BusinessTasks.RestockItemController;
import Controller.Date.DateController;
import Controller.Item.ItemController;
import Exceptions.Access.UnauthorizedAccessException;
import Exceptions.Tasks.RestockItem.RestockQuantityAndDateException;
import Model.Item.Item;
import UI.Components.Fields.JDateField;
import UI.Components.Fields.SearchListPanel;
import UI.Components.GridBagLayoutHelper;
import UI.Threads.LoadingThread;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class RestockItemPanel extends JPanel {

    private final SearchListPanel<Item> itemSearch;
    private final JDateField dateField;
    private final JPanel restockItemsContainer;

    public RestockItemPanel() {
        setLayout(new BorderLayout());

        itemSearch = new SearchListPanel<>(loadItems(), Item::getLabel);
        itemSearch.getSearchField().setPlaceholder("Search for an item");

        dateField = new JDateField();
        dateField.setPlaceholder("Enter restock date");
        dateField.setMinDate(new Date());

        JButton restockButton = new JButton("Restock");
        restockButton.addActionListener(e -> onRestock());

        GridBagLayoutHelper layoutHelper = new GridBagLayoutHelper();
        layoutHelper.addField("Select an item", itemSearch);
        layoutHelper.addField("Restock date", dateField);
        layoutHelper.addField(restockButton);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(layoutHelper, BorderLayout.NORTH);

        restockItemsContainer = new JPanel();
        restockItemsContainer.setLayout(new BoxLayout(restockItemsContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(restockItemsContainer);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private ArrayList<Item> loadItems() {
        try {
            return ItemController.getAllItems();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load items: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }

    private void onRestock()
    {
        if (itemSearch.getSelectedItem() == null || dateField.getDate() == null)
        {
            JOptionPane.showMessageDialog(this, "Please select an item and enter a date.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int randomWait = (int) (Math.random() * 10000);
        LoadingThread loadingThread = new LoadingThread("Restock Item", "Calculating restock quantities...", randomWait);

        loadingThread.start();

        ArrayList<Item.RestockItem> restockItem;
        try
        {
            restockItem = RestockItemController.calculateRestockQuantityAndDate(itemSearch.getSelectedItem(), dateField.getDate());
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

        loadingThread.onLoadingComplete(() -> displayRestockItems(restockItem));
    }

    private void displayRestockItems(ArrayList<Item.RestockItem> restockItems) {
        restockItemsContainer.removeAll();

        if (restockItems == null || restockItems.isEmpty()) {
            JLabel emptyLabel = new JLabel("No restock data available.");
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            restockItemsContainer.add(emptyLabel);
        } else {
            String[] columnNames = {"Quantity", "Date", "Event"};
            Object[][] data = new Object[restockItems.size()][3];

            for (int i = 0; i < restockItems.size(); i++) {
                Item.RestockItem item = restockItems.get(i);
                data[i][0] = item.getQuantity();
                data[i][1] = DateController.getDateString(item.getDate());
                data[i][2] = (item.getEvent() != null) ? item.getEvent().getLabel() : "N/A";
            }

            JTable table = new JTable(data, columnNames);
            table.setRowHeight(30);
            table.getTableHeader().setReorderingAllowed(false);
            table.setBackground(new Color(40, 40, 40));
            table.setForeground(Color.WHITE);
            table.getTableHeader().setBackground(new Color(48, 61, 78));
            table.getTableHeader().setForeground(Color.WHITE);
            table.setGridColor(Color.GRAY);
            table.setDefaultEditor(Object.class, null);

            JScrollPane scrollPane = new JScrollPane(table);
            restockItemsContainer.setLayout(new BorderLayout());
            restockItemsContainer.add(scrollPane, BorderLayout.CENTER);
        }

        restockItemsContainer.revalidate();
        restockItemsContainer.repaint();
    }
}
