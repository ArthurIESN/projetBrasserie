package UI.BusinessTasks;

import Controller.Customer.CustomerController;
import Controller.Item.ItemController;
import Controller.Locality.LocalityController;
import Exceptions.Customer.GetAllCustomersException;
import Exceptions.Item.GetAllItemsException;
import Model.Customer.Customer;
import Model.Item.Item;
import Model.Locality.Locality;
import UI.Components.Fields.JNumberField;
import UI.Components.Fields.MultipleSelectionList;
import UI.Components.Fields.SearchListPanel;
import UI.Components.GridBagLayoutHelper;
import UI.Components.RoundedPanel;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CustomerOrderPanel extends JPanel
{
    private JScrollPane scrollPane;
    private JPanel numberFieldPanel;
    private RoundedPanel VatPanel;
    private SearchListPanel<Customer> customerSearch;
    private SearchListPanel<Locality> customerLocalitySearch;
    private MultipleSelectionList<Item> itemList;
    private GridBagLayoutHelper gridCommand;
    private final int[] vatValues = {0, 0, 0, 0};
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

        itemList = new MultipleSelectionList<>(items, Item::getLabel);
        itemList.setOnSelectionChange(selectedItems ->
        {
            updateFieldsQuantity(itemList.getSelectedItems());
            calculateTaxes();
        });


        JButton commandButton = new JButton("Execute Command");
        commandButton.addActionListener(e -> executeOrder());

        gridCommand = new GridBagLayoutHelper();
        gridCommand.addField("Select a customer", customerSearch);
        gridCommand.addField("Select a locality", customerLocalitySearch);
        gridCommand.addField("Select items", itemList);
        gridCommand.addField(commandButton);

        rightPanel.add(gridCommand);

        add(rightPanel, BorderLayout.CENTER);
    }

    private void createVatPanel()
    {
        VatPanel = new RoundedPanel(0, 20, 0, 20, 2);
        VatPanel.setPreferredSize(new Dimension(250, 600));
        VatPanel.setLayout(new BoxLayout(VatPanel, BoxLayout.Y_AXIS));
        VatPanel.setBackground(new Color(73, 73, 73));

        JLabel title = new JLabel("SUMMARY");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        VatPanel.add(title);
        VatPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel vatContainer = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        vatContainer.add(VatPanel, gbc);

        String[] vatHeaders= {"TVA:", "TVAC: ", "TOTAL: ", "ETC: "};
        Color[] colors = {Color.DARK_GRAY, new Color(50, 50, 50), new Color(70, 70, 70), new Color(90, 90, 90)};

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

            VatPanel.add(vatPanel);
            VatPanel.add(Box.createRigidArea(new Dimension(0, 10)));
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

        for(Item item : items){
            if(!numberFieldHashMap.containsKey(item))
            {
                JLabel label = new JLabel(item.getLabel());

                JNumberField numberField = new JNumberField(Utils.NumberType.INTEGER);
                numberField.setPlaceholder("Enter quantity");
                numberField.setAllowNegative(false);
                numberField.setMinMax(0, 100);
                numberField.onFocusLost(e ->
                {
                    calculateTaxes();
                });

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(1, 2));
                panel.setPreferredSize(new Dimension(0, 50));
                panel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
                panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                panel.add(label);
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
                numberFieldPanel.remove(fieldToRemove);
                iterator.remove();
            }
        }

        scrollPane.revalidate();
        scrollPane.repaint();

    }

    // this needs to be in the business package
    private void calculateTaxes()
    {
        float totalItemsPrice = 0;
        float totalVat = 0;
        float totalTtc = 0;

        for (Item item : numberFieldHashMap.keySet())
        {
            JNumberField field = (JNumberField) numberFieldHashMap.get(item).getComponent(1);
            if (field != null)
            {
                float quantity = field.getFloat();
                float price = item.getPrice();
                totalItemsPrice += quantity * price;
            }
        }

        vatLabels.getFirst().setText(totalItemsPrice * 0.2f + " €");
    }

    private void executeOrder()
    {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to execute this order?", "Confirm Order", JOptionPane.YES_NO_OPTION);

        if (option != JOptionPane.YES_OPTION) return;
        if(!isOrderValid()) return;



        ArrayList<Item> selectedItems = itemList.getSelectedItems();
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

        for (Item item : numberFieldHashMap.keySet())
        {
            JNumberField field = (JNumberField) numberFieldHashMap.get(item).getComponent(1);
            if (field == null || field.getInt() <= 0)
            {
                JOptionPane.showMessageDialog(this, "Please enter a valid quantity for item: " + item.getLabel(), "Invalid Quantity", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
