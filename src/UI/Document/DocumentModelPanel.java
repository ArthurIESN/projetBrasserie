package UI.Document;

import Controller.Customer.CustomerController;
import Controller.DeliveryTruck.DeliveryTruckController;
import Controller.Document.DocumentController;
import Controller.DocumentStatus.DocumentStatusController;
import Controller.Item.ItemController;
import Controller.Process.ProcessController;
import Controller.Supplier.SupplierController;
import Controller.Vat.VATController;
import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DeliveryTruck.GetAllDeliveryTrucksException;
import Exceptions.Document.GetAllDocumentsException;
import Exceptions.DocumentStatus.GetAllDocumentStatusException;
import Exceptions.Item.GetAllItemsException;
import Exceptions.Process.GetProcessWithSpecificType;
import Exceptions.Supplier.GetAllSuppliersException;
import Exceptions.Vat.GetAllVatsException;
import Model.Customer.Customer;
import Model.DeliveryTruck.DeliveryTruck;
import Model.Document.Document;
import Model.DocumentStatus.DocumentStatus;
import Model.Item.Item;
import Model.Process.Process;
import Model.Supplier.Supplier;
import Model.Vat.Vat;
import UI.Components.Fields.*;
import UI.Components.GridBagLayoutHelper;
import static java.util.Arrays.asList;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Utils.Utils;

public class DocumentModelPanel extends JPanel {
    private final JPanel numberFieldPanel;
    private final JPanel searchListsVatPanel;
    private final JPanel depositPanel;

    private final GridBagLayoutHelper gridDocument;

    private final JEnhancedTextField labelField;
    private final JDateField deliveryDateField;
    private final JDateField desiredDeliveryDate;
    private String typeDocument;
    private final JDateField dateField;
    private JButton calculateVatButton;
    private final JNumberField reductionField;
    private final JNumberField depositAmountField;
    private final JButton button;

    private final JLabel totalExclVatLabel;
    private final JLabel totalInclVatLabel;
    private final JLabel totalVatLabel;
    private final JLabel depositAmountLabel;

    private final boolean updateDocument;
    private float totalExcludingTax;
    private float totalIncludingTax;
    private float totalVatAmount;
    private float reduction;
    private float depositAmount;

    private final ArrayList<JComponent> allComponents;
    private ArrayList<Process> processes;
    private ArrayList<Document> documents;

    private final SearchListPanel<DocumentStatus> documentStatusSearch;
    private final SearchListPanel<Process> processesSearch;
    private SearchListPanel<Customer> customerSearch;
    private final SearchListPanel<DeliveryTruck> deliveryTruckSearch;
    private SearchListPanel<Supplier> supplierSearch;
    private SearchListPanel<Document> documentSearch;
    MultipleSelectionList<Item> multipleSelectionListItems;

    private final ComboBoxPanel<Vat> comboBoxVat;
    private final ComboBoxPanel<String> comboBoxValidity;

    private final JCheckBox checkBoxDepositIsPaid;
    private final JCheckBox checkBoxIsDelivered;

    private final HashMap<Item, JNumberField> numberFieldHashMap;
    private final HashMap<Integer, ArrayList<Integer>> modelsDocuments;
    private final HashMap<Integer, ArrayList<JLabel>> vatItemHashMap;
    private final HashMap<Integer, ArrayList<JPanel>> panelsVatFieldsHashMap;


    public DocumentModelPanel(boolean updateDocument) {
        setLayout(new BorderLayout());

        this.updateDocument = updateDocument;

        Date today = new Date();

        processes = new ArrayList<>();
        allComponents = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<DocumentStatus> documentStatuses = new ArrayList<>();
        ArrayList<Vat> vats = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        ArrayList<DeliveryTruck> deliveryTrucks = new ArrayList<>();

        // @todo : ajouter une exception pour getAllDeliveryTrucks
        try {
            if(updateDocument){
                documents = DocumentController.getAllDocuments();
            }

            customers = CustomerController.getAllCustomers();
            deliveryTrucks = DeliveryTruckController.getAllDeliveryTrucks();
            suppliers = SupplierController.getAllSuppliers();
            vats = VATController.getAllVats();
            items = ItemController.getAllItems();
            documentStatuses = DocumentStatusController.getAllDocumentStatus();

        } catch (GetAllCustomersException | GetAllDocumentsException | GetAllSuppliersException
                 | GetAllVatsException | GetAllItemsException | GetAllDocumentStatusException |
                 GetAllDeliveryTrucksException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


        modelsDocuments = new HashMap<>();
        vatItemHashMap = new HashMap<>();
        panelsVatFieldsHashMap = new HashMap<>();

        JPanel westPanel = new JPanel(new BorderLayout());
        westPanel.setVisible(false);

        numberFieldPanel = new JPanel();
        numberFieldPanel.setVisible(false);
        numberFieldPanel.setLayout(new BoxLayout(numberFieldPanel, BoxLayout.Y_AXIS));
        numberFieldPanel.setBorder(BorderFactory.createTitledBorder("Number Field"));

        gridDocument = new GridBagLayoutHelper();

        if(updateDocument){
            documentSearch = new SearchListPanel<>(documents,Document::getLabel);
            documentSearch.getSearchField().setPlaceholder("Select Document");

            documentSearch.onSelectedItemChange(e -> {
                ArrayList<JComponent> componentsToExclude =  new ArrayList<>(asList(
                        customerSearch,
                        supplierSearch,
                        multipleSelectionListItems,
                        numberFieldPanel,
                        calculateVatButton
                ));

                for (Component component : gridDocument.getComponents()) {
                    if (!componentsToExclude.contains(component)) {
                        component.setVisible(true);
                    }
                }
            });

            gridDocument.addField(documentSearch);
        }

        searchListsVatPanel = new JPanel();
        searchListsVatPanel.setLayout(new BoxLayout(searchListsVatPanel, BoxLayout.Y_AXIS));
        searchListsVatPanel.setBorder(BorderFactory.createTitledBorder("VAT Panel"));

        depositPanel = new JPanel();
        depositPanel.setLayout(new BoxLayout(depositPanel, BoxLayout.Y_AXIS));
        depositPanel.setBorder(BorderFactory.createTitledBorder("Deposit Panel"));
        depositPanel.setVisible(false);

        calculateVatButton = new JButton("Calculate");
        calculateVatButton.addActionListener(e -> updateCalculVat());

        deliveryDateField = new JDateField();
        deliveryDateField.setPlaceholder("Delivery Date");
        deliveryDateField.setVisible(false);

        desiredDeliveryDate = new JDateField();
        desiredDeliveryDate.setPlaceholder("Desired Delivery Date");
        desiredDeliveryDate.setVisible(false);

        reductionField = new JNumberField(Utils.NumberType.FLOAT, 2);
        reductionField.setMinMax(0, 100);
        reductionField.setPlaceholder("Enter reduction for all items");
        reductionField.setVisible(false);

        reductionField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                reduction = reductionField.getFloat();
            }
        });

        ArrayList<String> optionsValidity = new ArrayList<>(asList("Valid", "Invalid"));
        comboBoxValidity = new ComboBoxPanel<>(optionsValidity, String::toString);
        comboBoxValidity.setVisible(false);

        checkBoxDepositIsPaid = new JCheckBox("Deposit is paid ?");

        checkBoxDepositIsPaid.addItemListener(e -> showFieldDepositAmount(e.getStateChange() == ItemEvent.SELECTED));


        depositAmountField = new JNumberField(Utils.NumberType.FLOAT, 2);
        depositAmountField.setPlaceholder("Enter deposit amount");
        depositAmountField.setVisible(false);

        depositAmountField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                depositAmount = depositAmountField.getFloat();
            }
        });

        depositPanel.add(checkBoxDepositIsPaid);
        depositPanel.add(depositAmountField);

        numberFieldHashMap = new HashMap<>();

        customerSearch = new SearchListPanel<>(customers, Customer::getFullName);
        customerSearch.setVisible(false);
        customerSearch.getSearchField().setPlaceholder("Select Customer");

        deliveryTruckSearch = new SearchListPanel<>(deliveryTrucks, DeliveryTruck::getLicensePlate);
        deliveryTruckSearch.setVisible(false);
        deliveryTruckSearch.getSearchField().setPlaceholder("Select Delivery Truck");

        supplierSearch = new SearchListPanel<>(suppliers, Supplier::getName);
        supplierSearch.setVisible(false);
        supplierSearch.getSearchField().setPlaceholder("Select Supplier");


        comboBoxVat = new ComboBoxPanel<>(vats, Vat::getCode);

        JLabel titleTotalExcludingTax = new JLabel("Total Excluding Tax: ");
        JLabel titleTotalIncludingTax = new JLabel("Total Including Tax: ");
        JLabel titleTotalVat = new JLabel("Total VAT: ");
        JLabel titleDepositAmount = new JLabel("Deposit Amount: ");

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS));
        totalPanel.setBorder(BorderFactory.createTitledBorder("Total Panel"));

        JPanel totalExcludingTaxPanel = new JPanel();
        totalExcludingTaxPanel.setLayout(new BoxLayout(totalExcludingTaxPanel, BoxLayout.X_AXIS));

        JPanel totalIncludingTaxPanel = new JPanel();
        totalIncludingTaxPanel.setLayout(new BoxLayout(totalIncludingTaxPanel, BoxLayout.X_AXIS));

        JPanel totalVatAmountPanel = new JPanel();
        totalVatAmountPanel.setLayout(new BoxLayout(totalVatAmountPanel, BoxLayout.X_AXIS));

        JPanel totalDepositAmountPanel = new JPanel();
        totalDepositAmountPanel.setLayout(new BoxLayout(totalDepositAmountPanel, BoxLayout.X_AXIS));

        depositAmountLabel = new JLabel();
        depositAmountLabel.setFont(new Font(depositAmountLabel.getFont().getName(), Font.BOLD, 16));

        totalExclVatLabel = new JLabel();
        totalExclVatLabel.setFont(new Font(totalExclVatLabel.getFont().getName(), Font.BOLD, 16));

        totalInclVatLabel = new JLabel();
        totalInclVatLabel.setFont(new Font(totalInclVatLabel.getFont().getName(), Font.BOLD, 16));

        totalVatLabel = new JLabel();
        totalVatLabel.setFont(new Font(totalVatLabel.getFont().getName(), Font.BOLD, 16));

        totalExcludingTaxPanel.add(titleTotalExcludingTax);
        totalExcludingTaxPanel.add(totalExclVatLabel);

        totalIncludingTaxPanel.add(titleTotalIncludingTax);
        totalIncludingTaxPanel.add(totalInclVatLabel);

        totalVatAmountPanel.add(titleTotalVat);
        totalVatAmountPanel.add(totalVatLabel);

        totalDepositAmountPanel.add(titleDepositAmount);
        totalDepositAmountPanel.add(depositAmountLabel);

        totalPanel.add(totalExcludingTaxPanel);
        totalPanel.add(totalIncludingTaxPanel);
        totalPanel.add(totalVatAmountPanel);
        totalPanel.add(totalDepositAmountPanel);


        multipleSelectionListItems = new MultipleSelectionList<>(items, Item::getLabel);

        multipleSelectionListItems.setVisible(false);


        multipleSelectionListItems.setOnSelectionChange(selectedItems -> updateFieldsQuantity(multipleSelectionListItems.getSelectedItems()));


        // 0
        allComponents.add(deliveryDateField);
        // 1
        allComponents.add(desiredDeliveryDate);
        // 2
        allComponents.add(multipleSelectionListItems);
        // 3
        allComponents.add(numberFieldPanel);
        //4
        allComponents.add(westPanel);
        //5
        allComponents.add(reductionField);
        //6
        allComponents.add(comboBoxValidity);
        // 7
        allComponents.add(depositPanel);
        // 8 customer Search
        allComponents.add(customerSearch);
        //9
        allComponents.add(deliveryTruckSearch);
        // 10
        allComponents.add(supplierSearch);


        // Model pour la commande fournisseur (1)
        modelsDocuments.put(0, new ArrayList<>(asList(0, 1, 2, 3, 4, 10)));
        // Model pour la commande client (2)
        modelsDocuments.put(1, new ArrayList<>(asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));

        labelField = new JEnhancedTextField();
        labelField.setPlaceholder("Label document");
        labelField.setEditable(false);
        labelField.setCanClear(false);

        dateField = new JDateField();
        dateField.setDate(today);

        checkBoxIsDelivered = new JCheckBox("Is Delivered ?");

        documentStatusSearch = new SearchListPanel<>(documentStatuses, DocumentStatus::getLabel);
        documentStatusSearch.getSearchField().setPlaceholder("Select Document Status");

        processesSearch = new SearchListPanel<>(processes, Process::getLabel);
        processesSearch.getSearchField().setPlaceholder("Select Process");
        processesSearch.setVisible(false);

        comboBoxVat.onSelectedItemChange(vatChange -> updateCalculVat());

        button = new JButton();

        gridDocument.addField(labelField);
        gridDocument.addField(dateField);
        gridDocument.addField(checkBoxIsDelivered);
        gridDocument.addField(documentStatusSearch);
        gridDocument.addField(processesSearch);
        for (JComponent component : allComponents) {
            gridDocument.addField(component);
        }
        gridDocument.addField(multipleSelectionListItems);
        gridDocument.addField(numberFieldPanel);
        gridDocument.addField(calculateVatButton);
        gridDocument.addField(button);

        searchListsVatPanel.add(comboBoxVat);
        searchListsVatPanel.add(totalPanel);

        westPanel.add(searchListsVatPanel, BorderLayout.NORTH);

        if(updateDocument)
        {
            for(int i = 1; i < gridDocument.getComponents().length; i++)
            {
                gridDocument.getComponents()[i].setVisible(false);
            }
        }

        add(gridDocument, BorderLayout.CENTER);
        add(westPanel, BorderLayout.WEST);

    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
        setLabelField(typeDocument);
    }

    private void showFieldDepositAmount(boolean show)
    {
        depositAmountField.setVisible(show);

        depositPanel.revalidate();
        depositPanel.repaint();
    }

    //@todo : mettre dans la couche buisness logique
    private void calculTotalVat() {
        totalExcludingTax = 0;
        totalIncludingTax = 0;
        totalVatAmount = 0;

        for (Map.Entry<Integer, ArrayList<JLabel>> entry : vatItemHashMap.entrySet()) {
            ArrayList<JLabel> labelList = entry.getValue();

            for (int i = 0; i < labelList.size(); i++) {
                float value = Float.parseFloat(labelList.get(i).getText().replace(",", "."));
                if (i == 0) {
                    totalExcludingTax += value;
                } else if (i == 1) {
                    totalIncludingTax += value;
                } else if (i == 2) {
                    totalVatAmount += value;
                }
            }
        }

        if (reduction > 0) {
            float reductionAmount = (totalExcludingTax * reduction) / 100;
            totalExcludingTax -= reductionAmount;

            totalVatAmount = totalExcludingTax * (comboBoxVat.getSelectedItem().getRate() / 100);

            totalIncludingTax = totalExcludingTax + totalVatAmount;
        }

        if (depositAmount > 0) {
            totalIncludingTax -= depositAmount;

            if (totalIncludingTax < 0) {
                totalIncludingTax = 0;
            }
        }

    }

    //@todo : mettre dans la couche buisness logique
    private float[] calculVat(float unitPrice, float quantity, float vatRate) {
        float[] result = new float[3];

        float totalExcludingTax = unitPrice * quantity;
        float totalIncludingTax = totalExcludingTax * (1 + vatRate / 100);
        float vatAmount = totalIncludingTax - totalExcludingTax;

        result[0] = totalExcludingTax;
        result[1] = totalIncludingTax;
        result[2] = vatAmount;

        return result;
    }

    private void updateCalculVat() {

        ArrayList<JLabel> tvaFields;
        ArrayList<JPanel> panelsVatFields;

        Iterator<Integer> iterator = vatItemHashMap.keySet().iterator();
        while (iterator.hasNext()) {
            Integer id = iterator.next();
            if (!multipleSelectionListItems.getSelectedItems().stream().anyMatch(item -> item.getId().equals(id))) {
                ArrayList<JPanel> panelsToRemove = panelsVatFieldsHashMap.get(id);
                for (JPanel panelVat : panelsToRemove) {
                    searchListsVatPanel.remove(panelVat);
                }
                iterator.remove();
            }
        }

        for (Item item : multipleSelectionListItems.getSelectedItems()) {
            float quantity = numberFieldHashMap.get(item).getFloat();

            if (quantity > 0) {
                float unitPrice = item.getPrice();
                float vatRate = comboBoxVat.getSelectedItem().getRate();

                float[] values = calculVat(unitPrice,quantity,vatRate);

                if (vatItemHashMap.containsKey(item.getId())) {
                    tvaFields = vatItemHashMap.get(item.getId());
                    tvaFields.get(0).setText(String.format("%.2f", values[0]));
                    tvaFields.get(1).setText(String.format("%.2f", values[1]));
                    tvaFields.get(2).setText(String.format("%.2f", values[2]));
                } else {
                    tvaFields = new ArrayList<>();
                    panelsVatFields = new ArrayList<>();

                    JPanel totalExcludingTaxPanel = new JPanel();
                    totalExcludingTaxPanel.setLayout(new BoxLayout(totalExcludingTaxPanel, BoxLayout.X_AXIS));
                    JLabel totalExcludingTaxLabel = new JLabel("excl. VAT for " + item.getLabel() + ":  ");
                    JLabel totalExcludingTax = new JLabel();
                    totalExcludingTax.setFont(new Font(totalExcludingTax.getFont().getName(), Font.BOLD, 16));
                    totalExcludingTax.setPreferredSize(new Dimension(200, 30));
                    totalExcludingTaxPanel.add(totalExcludingTaxLabel);
                    totalExcludingTaxPanel.add(totalExcludingTax);

                    JPanel totalInclusiveOfTaxPanel = new JPanel();
                    totalInclusiveOfTaxPanel.setLayout(new BoxLayout(totalInclusiveOfTaxPanel, BoxLayout.X_AXIS));
                    JLabel totalInclusiveOfTaxLabel = new JLabel("incl. VAT for " + item.getLabel() + ":  ");
                    JLabel totalInclusiveOfTax = new JLabel();
                    totalInclusiveOfTax.setFont(new Font(totalInclusiveOfTax.getFont().getName(), Font.BOLD, 16));
                    totalInclusiveOfTax.setPreferredSize(new Dimension(200, 30));
                    totalInclusiveOfTaxPanel.add(totalInclusiveOfTaxLabel);
                    totalInclusiveOfTaxPanel.add(totalInclusiveOfTax);

                    JPanel vatAmountPanel = new JPanel();
                    vatAmountPanel.setLayout(new BoxLayout(vatAmountPanel, BoxLayout.X_AXIS));
                    JLabel vatAmountLabel = new JLabel("VAT amount for " + item.getLabel() + ":  ");
                    JLabel vatAmount = new JLabel();
                    vatAmount.setFont(new Font(vatAmount.getFont().getName(), Font.BOLD, 16));
                    vatAmount.setPreferredSize(new Dimension(200, 30));

                    vatAmountPanel.add(vatAmountLabel);
                    vatAmountPanel.add(vatAmount);

                    panelsVatFields.add(totalExcludingTaxPanel);
                    panelsVatFields.add(totalInclusiveOfTaxPanel);
                    panelsVatFields.add(vatAmountPanel);


                    totalExcludingTax.setText(String.format("%.2f", values[0]));
                    totalInclusiveOfTax.setText(String.format("%.2f", values[1]));
                    vatAmount.setText(String.format("%.2f", values[2]));

                    tvaFields.add(totalExcludingTax);
                    tvaFields.add(totalInclusiveOfTax);
                    tvaFields.add(vatAmount);


                    vatItemHashMap.put(item.getId(), tvaFields);
                    panelsVatFieldsHashMap.put(item.getId(), panelsVatFields);

                    for (JPanel vatPanel : panelsVatFields) {
                        searchListsVatPanel.add(vatPanel);
                    }
                }
            }
        }

        calculTotalVat();

        totalExclVatLabel.setText(String.format("%.2f", totalExcludingTax) + " €");
        totalInclVatLabel.setText(String.format("%.2f", totalIncludingTax) + " €");
        totalVatLabel.setText(String.format("%.2f", totalVatAmount) + " €");
        depositAmountLabel.setText(String.format("%.2f", depositAmount) + " €");

        searchListsVatPanel.revalidate();
        searchListsVatPanel.repaint();
    }

    private void setLabelField(String label) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmms");
        String formattedDateTime = now.format(formatter);
        labelField.updateText(label + formattedDateTime);
    }

    public void loadDataAndShowProcesses(Integer id) {
        try {
            processes = ProcessController.getProcessWithSpecificType(id);
            if (!processes.isEmpty()) {
                processesSearch.setData(processes);
                processesSearch.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No processes found for the selected type.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (GetProcessWithSpecificType e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setAllCompnentsVisibleFalse() {
        allComponents.stream().forEach(x -> x.setVisible(false));
        gridDocument.revalidate();
        gridDocument.repaint();
    }

    public void update() {
        setAllCompnentsVisibleFalse();
        reduction = 0;
        ArrayList<Integer> indexes;
        switch (this.typeDocument) {
            case "Customer Order":
                indexes = modelsDocuments.get(1);
                for (Integer index : indexes) {
                    allComponents.get(index).setVisible(true);
                }
                break;
            case "Order":
                indexes = modelsDocuments.get(0);
                for (Integer index : indexes) {
                    allComponents.get(index).setVisible(true);
                }
                break;
        }

    }

    private void updateFieldsQuantity(ArrayList<Item> items)
    {

        for (Item item : items)
        {
            if (!numberFieldHashMap.containsKey(item))
            {
                JNumberField numberField = new JNumberField(Utils.NumberType.FLOAT, 2);
                numberField.setAllowNegative(false);
                numberField.setPlaceholder("Enter quantity");

                numberFieldHashMap.put(item, numberField);
                numberFieldPanel.add(numberField);
            }
        }

        Iterator<Item> iterator = numberFieldHashMap.keySet().iterator();

        while (iterator.hasNext())
        {
            Item item = iterator.next();
            if (!items.contains(item))
            {
                numberFieldPanel.remove(numberFieldHashMap.get(item));
                iterator.remove();
            }
        }

        numberFieldPanel.revalidate();
        numberFieldPanel.repaint();
    }

    public boolean isDocumentInvalid() {

        if (Objects.equals(typeDocument, "order")) {
            if (desiredDeliveryDate.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in the desired delivery date", "Error", JOptionPane.ERROR_MESSAGE);
                return true;
            }

            if (comboBoxValidity.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Please select a validity", "Error", JOptionPane.ERROR_MESSAGE);
                return true;
            }

            if (customerSearch.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Please select a customer", "Error", JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }

        if (Objects.equals(typeDocument, "Order")) {
            if (supplierSearch.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Please select a supplier", "Error", JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }


        if (dateField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the date", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (labelField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the label", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }



        if (documentStatusSearch.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please select a document status", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (processesSearch.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please select a process", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if(!updateDocument) {
            if (multipleSelectionListItems.getSelectedItems().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select at least one item", "Error", JOptionPane.ERROR_MESSAGE);
                return true;
            }

            for (Item item : multipleSelectionListItems.getSelectedItems()) {
                int id = item.getId();
                if (!numberFieldHashMap.containsKey(id)) {
                    JOptionPane.showMessageDialog(this, "Please fill in the quantity for item " + item.getLabel(), "Error", JOptionPane.ERROR_MESSAGE);
                    return true;
                }
            }
        }

        return false;
    }

    public JDateField getDateField() {
        return dateField;
    }

    public JDateField getDeliveryDateField() {
        return deliveryDateField;
    }

    public JDateField getDesiredDeliveryDate() {
        return desiredDeliveryDate;
    }

    public JEnhancedTextField getLabelField() {
        return labelField;
    }

    public boolean getIsDelivered() {
        return checkBoxIsDelivered.isSelected();
    }

    public JCheckBox getCheckBoxIsDelivered() {
        return checkBoxIsDelivered;
    }

    public SearchListPanel<DocumentStatus> getDocumentStatusSearch() {
        return documentStatusSearch;
    }

    public SearchListPanel<Process> getProcessesSearch() {
        return processesSearch;
    }

    public SearchListPanel<Supplier> getSupplierSearch() {
        return supplierSearch;
    }

    public MultipleSelectionList<Item> getMultipleSelectionListItems() {
        return multipleSelectionListItems;
    }

    public HashMap<Item, JNumberField> getNumberFieldHashMap() {
        return numberFieldHashMap;
    }

    public ComboBoxPanel<String> getComboBoxValidity() {
        return comboBoxValidity;
    }

    public SearchListPanel<Customer> getCustomerSearch() {
        return customerSearch;
    }

    public float getReduction(){
        return reduction;
    }

    public JNumberField getReductionField(){
        return reductionField;
    }

    public JCheckBox getCheckBoxDepositIsPaid() {
        return checkBoxDepositIsPaid;
    }

    public float getDepositAmount() {
        return depositAmount;
    }

    public JNumberField getDepositAmountField(){
        return  depositAmountField;
    }

    public ComboBoxPanel<Vat> getComboBoxVat() {
        return comboBoxVat;
    }

    public float getTotalIncludingTax(){
        return totalIncludingTax;
    }

    public float getTotalExcludingTax(){
        return totalExcludingTax;
    }

    public float getTotalVatAmount(){
        return totalVatAmount;
    }

    public SearchListPanel<Document> getDocumentSearch() {
        return documentSearch;
    }

    public SearchListPanel<DeliveryTruck> getDeliveryTruckSearch() {
        return deliveryTruckSearch;
    }

    public void setTextButton(String text){
        button.setText(text);
    }

    public void onButtonClicked(ActionListener actionListener){
        button.addActionListener(actionListener);
    }

    public void onSearchDocumentChange(ActionListener actionListener){
        documentSearch.onSelectedItemChange(actionListener);
    }
}
