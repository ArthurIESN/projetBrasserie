package UI.Search.Payment;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

import Controller.Payment.PaymentController;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.GetAllPaymentYearsException;
import Exceptions.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.Search.SearchPaymentException;
import Model.PaymentStatus.PaymentStatus;
import Model.ProcessType.ProcessType;
import UI.Components.EnhancedTable.TableModelMaker;
import UI.Components.Fields.ComboBoxGen;
import UI.Components.Fields.ComboBoxPanel;
import UI.Components.Fields.JNumberField;
import UI.Components.GridBagLayoutHelper;
import Controller.SearchController;
import Model.Payment.Payment;
import UI.Components.EnhancedTable.JEnhancedTableScrollPanel;
import UI.Models.*;
import Model.Document.Document;
import Model.Process.Process;
import Model.ProcessStatus.ProcessStatus;
import Model.Customer.Customer;
import UI.Models.Document.DocumentEnhancedTableModel;
import UI.Models.Document.SearchPaymentDocumentEnhancedTableModel;
import Utils.Utils;

public class SearchPaymentForm extends JPanel
{
    private JEnhancedTableScrollPanel tableScrollPanel;
    private TableModelMaker tableModelMaker;
    private PaymentEnhancedTableModel paymentTableModel;
    private PaymentStatusEnhancedTableModel paymentStatusTableModel;
    private SearchPaymentDocumentEnhancedTableModel documentTableModel;
    private ProcessTypeEnhancedTableModel processTypesTableModel;
    private ProcessEnhancedTableModel processTableModel;
    private CustomerEnhancedTableModel customerTableModel;


    public SearchPaymentForm()
    {
        ArrayList<Integer> paymentYears;
        try {
            // Récupérer les années depuis la base de données
            paymentYears = PaymentController.getAllPaymentYears();
        } catch (GetAllPaymentYearsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            paymentYears = new ArrayList<>(); // Liste vide en cas d'erreur
        }

        // add a title
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Search Payments");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        GridBagLayoutHelper gridSearchForm = new GridBagLayoutHelper();

        // Payment Validated Checkbox
        JCheckBox validatedPaymentCheckBox = new JCheckBox();
        gridSearchForm.addField("Payment validated", validatedPaymentCheckBox);

        // Minimum Amount Field
        JNumberField amountField = new JNumberField(Utils.NumberType.FLOAT, 2);
        amountField.setAllowNegative(false);
        amountField.setPlaceholder("Enter amount");
        amountField.setColumns(10);
        gridSearchForm.addField("Minimum amount", amountField);

        // Year Selection ComboBox
        JComboBox<Integer> yearComboBox;
        yearComboBox = new JComboBox<>(paymentYears.toArray(new Integer[0]));
        if (!paymentYears.isEmpty()) {
            yearComboBox.setSelectedIndex(0); // Select the first year by default
        }
        gridSearchForm.addField("Select Year", yearComboBox);
        add(gridSearchForm, BorderLayout.CENTER);

        // add a button to search
        JButton searchButton = new JButton("Start search");
        gridSearchForm.addField(searchButton);

        // Empty table
        tableModelMaker = new TableModelMaker();
        paymentTableModel = new PaymentEnhancedTableModel(new ArrayList<>());
        paymentStatusTableModel = new PaymentStatusEnhancedTableModel(new ArrayList<>());
        documentTableModel = new SearchPaymentDocumentEnhancedTableModel(new ArrayList<>());
        processTypesTableModel = new ProcessTypeEnhancedTableModel(new ArrayList<>());
        processTableModel = new ProcessEnhancedTableModel(new ArrayList<>());
        customerTableModel = new CustomerEnhancedTableModel(new ArrayList<>());

        tableModelMaker.addTableModel(paymentTableModel);
        tableModelMaker.addTableModel(paymentStatusTableModel);
        tableModelMaker.addTableModel(documentTableModel);
        tableModelMaker.addTableModel(processTypesTableModel);
        tableModelMaker.addTableModel(processTableModel);
        tableModelMaker.addTableModel(customerTableModel);

        tableScrollPanel = new JEnhancedTableScrollPanel(tableModelMaker, this);
        tableModelMaker.setTable(tableScrollPanel);
        add(tableScrollPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {
            searchPayments(validatedPaymentCheckBox, amountField, yearComboBox, tableScrollPanel);
        });

    }

    private void searchPayments(JCheckBox validatedPaymentCheckBox, JNumberField amountField, JComboBox<Integer> yearComboBox, JEnhancedTableScrollPanel table)
    {
        boolean isValidated = validatedPaymentCheckBox.isSelected();
        Float minAmountValue =  amountField.getFloat();
        double minAmount = (minAmountValue != null) ? minAmountValue.doubleValue() : 0;
        Integer selectedYear = (Integer) yearComboBox.getSelectedItem();

        String paymentStatus;
        if (isValidated){
            paymentStatus = "Validated";
        } else {
            paymentStatus = "Not validated";
        }

        java.sql.Date sqlDate = null;
        if (selectedYear != null) {
            sqlDate = java.sql.Date.valueOf(selectedYear + "-01-01"); // Convert YYYY to SQL Date
        }

        try {
            ArrayList<Payment> payment = PaymentController.searchPayments(paymentStatus, minAmount, sqlDate);
            ArrayList<PaymentStatus> paymentsStatus = Utils.transformData(payment, Payment::getPaymentStatus);
            ArrayList<Document> document = Utils.transformData(payment, Payment::getDocument);
            ArrayList<Process> process = Utils.transformData(payment, Payment::getProcess);
            ArrayList<ProcessType> processTypes = Utils.transformData(process, Process::getType);
            ArrayList<Customer> customer = Utils.transformData(payment, Payment::getCustomer);

            paymentTableModel.setData(payment);
            paymentStatusTableModel.setData(paymentsStatus);
            documentTableModel.setData(document);
            processTypesTableModel.setData(processTypes);
            processTableModel.setData(process);
            customerTableModel.setData(customer);

            tableScrollPanel.updateModel(tableModelMaker);

            if(payment.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "No payment found", "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SearchPaymentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}