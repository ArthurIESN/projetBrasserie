package UI.BusinessTasks;

import Controller.BusinessTasks.CustomerOrderController;
import Controller.Customer.CustomerController;
import Controller.Document.DocumentController;
import Controller.Item.ItemController;
import Controller.Locality.LocalityController;
import Exceptions.Access.UnauthorizedAccessException;
import Exceptions.Customer.GetAllCustomersException;
import Exceptions.Item.GetAllItemsException;
import Exceptions.Tasks.RestockItem.CustomerOrder.ExecuteOrderException;
import Model.Customer.Customer;
import Model.Item.Item;
import Model.Locality.Locality;
import UI.Components.Fields.JDateField;
import UI.Components.Fields.JNumberField;
import UI.Components.Fields.MultipleSelectionList;
import UI.Components.Fields.SearchListPanel;
import UI.Components.GridBagLayoutHelper;
import UI.Components.RoundedPanel;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CustomerOrderPanel extends JPanel
{
    private JScrollPane scrollPane;
    private JPanel numberFieldPanel;
    private SearchListPanel<Customer> customerSearch;
    private SearchListPanel<Locality> customerLocalitySearch;
    private MultipleSelectionList<Item> itemList;
    private JNumberField depositField;
    private JDateField desiredDeliveryDateField;
    private float[] vatValues = {0, 0, 0, 0};
    private final ArrayList<JLabel> vatLabels = new ArrayList<>();

    private HashMap<Item, JPanel> numberFieldHashMap;

    public CustomerOrderPanel()
    {
        setLayout(new BorderLayout());

        createVatPanel();
        createRightPanel();

        add(scrollPane, BorderLayout.SOUTH);
    }

    private void createRightPanel()
    {
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();

        try
        {
            customers = CustomerController.getAllCustomers();
            items = ItemController.getAllItems();
        }
        catch (GetAllCustomersException | GetAllItemsException e)
        {
            JOptionPane.showMessageDialog(this, "Failed to load data " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        numberFieldHashMap = new HashMap<>();

        scrollPane = new JScrollPane();
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setPreferredSize(new Dimension(0, 200));
        scrollPane.setMaximumSize(new Dimension(Short.MAX_VALUE, 200));


        numberFieldPanel = new JPanel();
        numberFieldPanel.setLayout(new BoxLayout(numberFieldPanel, BoxLayout.Y_AXIS));

        scrollPane.setViewportView(numberFieldPanel);

        customerSearch = new SearchListPanel<>(customers, customer -> customer.getFirstName() + " " + customer.getLastName() + " - " + customer.getId());
        customerSearch.getSearchField().setPlaceholder("Search for a customer");
        customerSearch.onSelectedItemChange(selectedCustomer -> updateCustomerLocalities());

        customerLocalitySearch = new SearchListPanel<>(new ArrayList<>(), locality -> locality.getAddress() + " " + locality.getPostalCode() + " " + locality.getNumber() + " " + locality.getCountry().getLabel());
        customerLocalitySearch.getSearchField().setPlaceholder("Search for a locality");
        customerLocalitySearch.onSelectedItemChange(selectedLocality ->
        {
            calculateTaxes();
        });

        itemList = new MultipleSelectionList<>(items, Item::getLabel);
        itemList.getSearchField().setPlaceholder("Search for items");
        itemList.setOnSelectionChange(selectedItems ->
        {
            updateFieldsQuantity(itemList.getSelectedItems());
            calculateTaxes();
        });

        depositField = new JNumberField(Utils.NumberType.FLOAT, 2);
        depositField.setPlaceholder("Enter deposit amount");
        depositField.setAllowNegative(false);
        depositField.setMinMax(0, 0);

        desiredDeliveryDateField = new JDateField();
        desiredDeliveryDateField.setPlaceholder("Enter desired delivery date");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000) * 2 ));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date minDate = calendar.getTime();

        desiredDeliveryDateField.setMinDate(minDate);


        JButton commandButton = new JButton("Execute Command");
        commandButton.addActionListener(e -> executeOrder());

        GridBagLayoutHelper gridCommand = new GridBagLayoutHelper();
        gridCommand.addField("Select a customer", customerSearch);
        gridCommand.addField("Select a locality", customerLocalitySearch);
        gridCommand.addField("Select items", itemList);
        gridCommand.addField("Deposit", depositField);
        gridCommand.addField("Desired delivery date", desiredDeliveryDateField);
        gridCommand.addField(commandButton);

        rightPanel.add(gridCommand);

        add(rightPanel, BorderLayout.CENTER);
    }

    private void checkItemQuantity(Item item, int quantity, JLabel messageLabel)
    {
        if (item.getCurrentQuantity() < quantity)
        {
            messageLabel.setText("Not enough quantity available. Available: " + item.getCurrentQuantity());
            messageLabel.setForeground(Color.RED);
        }
        else
        {
            messageLabel.setText("");
        }
    }

    private void createVatPanel()
    {
        RoundedPanel vatPanel1 = new RoundedPanel(0, 20, 0, 20, 2);
        vatPanel1.setPreferredSize(new Dimension(250, 600));
        vatPanel1.setLayout(new BoxLayout(vatPanel1, BoxLayout.Y_AXIS));
        vatPanel1.setBackground(new Color(73, 73, 73));

        JLabel title = new JLabel("SUMMARY");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        vatPanel1.add(title);
        vatPanel1.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel vatContainer = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        vatContainer.add(vatPanel1, gbc);

        String[] vatHeaders= {"VAT:", "VAT INCLUDED:", "DELIVERY COST:", "TOTAL:" };
        Color[] colors = {Color.DARK_GRAY, new Color(50, 50, 50), new Color(70, 70, 70), new Color(90, 90, 90), new Color(110, 110, 110)};

        for (int i = 0; i < vatHeaders.length; i++)
        {
            JPanel vatPanel = new JPanel();
            vatPanel.setOpaque(true);
            vatPanel.setBackground(new Color(73, 73, 73));

            JLabel label = new JLabel(vatHeaders[i]);
            createVatLabel(colors, i, label);

            JLabel valueLabel = new JLabel(vatValues[i] + " €");
            createVatLabel(colors, i, valueLabel);

            vatLabels.add(valueLabel);

            vatPanel.add(label);
            vatPanel.add(valueLabel);

            vatPanel1.add(vatPanel);
            vatPanel1.add(Box.createRigidArea(new Dimension(0, 10)));
        }


        add(vatContainer, BorderLayout.WEST);
    }

    private void createVatLabel(Color[] colors, int i, JLabel valueLabel)
    {
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        valueLabel.setOpaque(true);
        valueLabel.setBackground(colors[i]);
        valueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        valueLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    private void updateCustomerLocalities()
    {
        ArrayList<Locality> localities;

        try
        {
            localities = LocalityController.getCustomerLocalities(customerSearch.getSelectedItem().getId());
            customerLocalitySearch.setData(localities);

            if(localities.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "No localities found for this customer. No order will be available.", "No Localities", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Failed to load localities: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateFieldsQuantity(ArrayList<Item> items)
    {

        for(Item item : items)
        {
            if(!numberFieldHashMap.containsKey(item))
            {
                JLabel label = new JLabel(item.getLabel());

                JLabel messageLabel = new JLabel();

                JNumberField numberField = new JNumberField(Utils.NumberType.INTEGER);
                numberField.setPlaceholder("Enter quantity");
                numberField.setAllowNegative(false);
                numberField.setMinMax(0, 100);
                numberField.onFocusLost(e ->
                {
                    checkItemQuantity(item, numberField.getInt(), messageLabel);
                    calculateTaxes();
                });

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(1, 2));
                panel.setPreferredSize(new Dimension(0, 50));
                panel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
                panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                panel.add(label);
                panel.add(messageLabel);
                panel.add(numberField);
                numberFieldPanel.add(panel);
                numberFieldHashMap.put(item, panel);
            }
        }


        Iterator<Item> iterator = numberFieldHashMap.keySet().iterator();
        while (iterator.hasNext())
        {
            Item item = iterator.next();
            if (!items.contains(item))
            {
                JPanel fieldToRemove = numberFieldHashMap.get(item);

                if(fieldToRemove != null)
                {
                    numberFieldPanel.remove(fieldToRemove);
                }

                iterator.remove();
            }
        }

        scrollPane.revalidate();
        scrollPane.repaint();

    }

    private void calculateTaxes()
    {
        createItemsHashMap();
        vatValues = DocumentController.calculateTaxes(createItemsHashMap(), customerLocalitySearch.getSelectedItem());

        vatLabels.get(0).setText(String.format("%.2f €", vatValues[0]));
        vatLabels.get(1).setText(String.format("%.2f €", vatValues[1]));
        vatLabels.get(2).setText(String.format("%.2f €",    vatValues[2]));
        vatLabels.get(3).setText(String.format("%.2f €", vatValues[3]));

        depositField.setMinMax(0, (int)Math.ceil(vatValues[3]));
    }

    private void executeOrder()
    {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to execute this order?", "Confirm Order", JOptionPane.YES_NO_OPTION);

        if (option != JOptionPane.YES_OPTION) return;
        if(!isOrderValid()) return;

        try
        {
            CustomerOrderController.executeOrder(createItemsHashMap(), customerSearch.getSelectedItem(), vatValues, depositField.getFloat(), desiredDeliveryDateField.getDate());

            JOptionPane.showMessageDialog(this, "Order executed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            refreshPanel();
        }
        catch (UnauthorizedAccessException | ExecuteOrderException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private HashMap<Item, Integer> createItemsHashMap() {
        HashMap<Item, Integer> items = new HashMap<>();
        for (Item item : numberFieldHashMap.keySet())
        {
            JNumberField field = (JNumberField) numberFieldHashMap.get(item).getComponent(2);
            if (field != null)
            {
                items.put(item, field.getInt());
            }
        }
        return items;
    }

    private void refreshPanel()
    {
        // Clear or reset data in components
        customerSearch.setSelectedItem(null);
        customerLocalitySearch.setData(null);
        itemList.clearSelection();
        depositField.setText("");
        desiredDeliveryDateField.setDate(null);
        numberFieldHashMap.clear();
        numberFieldPanel.removeAll();

        // refresh vat values
        for (JLabel label : vatLabels)
        {
            label.setText("0.00 €");
        }


        vatValues = new float[]{0, 0, 0, 0};



        // Revalidate and repaint the panel
        scrollPane.revalidate();
        scrollPane.repaint();
        this.revalidate();
        this.repaint();
    }

    private boolean isOrderValid()
    {
        if(customerSearch.getSelectedItem() == null)
        {
            JOptionPane.showMessageDialog(this, "Please select a customer.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if(customerLocalitySearch.getSelectedItem() == null)
        {
            JOptionPane.showMessageDialog(this, "Please select a locality.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if(itemList.getSelectedItems().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please select at least one item.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        float minDepositAmount =  CustomerOrderController.getCustomerDepositMinimumAmount(customerSearch.getSelectedItem(), vatValues[3]);
        if(minDepositAmount > depositField.getFloat())
        {
            JOptionPane.showMessageDialog(this, "Deposit amount is too low. Minimum: " + minDepositAmount,  "Invalid Deposit", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if(desiredDeliveryDateField.getDate() == null)
        {
            JOptionPane.showMessageDialog(this, "Please select a desired delivery date.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        for (Item item : numberFieldHashMap.keySet())
        {
            JNumberField field = (JNumberField) numberFieldHashMap.get(item).getComponent(2);
            if ((field == null || field.getInt() <= 0))
            {
                JOptionPane.showMessageDialog(this, "Please enter a valid quantity for item: " + item.getLabel(), "Invalid Quantity", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            else if(field.getInt() > item.getCurrentQuantity())
            {
                JOptionPane.showMessageDialog(this, "Not enough quantity available for item: " + item.getLabel() + ". Available: " + item.getCurrentQuantity(), "Insufficient Quantity", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
