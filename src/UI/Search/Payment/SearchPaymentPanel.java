package UI.Search.Payment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Controller.Payment.PaymentController;
import Exceptions.Search.GetAllPaymentYearsException;
import Exceptions.Search.SearchPaymentException;
import Model.PaymentStatus.PaymentStatus;
import Model.ProcessType.ProcessType;
import UI.Components.EnhancedTable.TableModelMaker;
import UI.Components.Fields.ComboBoxPanel;
import UI.Components.Fields.JNumberField;
import UI.Components.GridBagLayoutHelper;
import Model.Payment.Payment;
import UI.Components.EnhancedTable.JEnhancedTableScrollPanel;
import UI.Models.*;
import Model.Document.Document;
import Model.Process.Process;
import Model.Customer.Customer;
import UI.Models.Document.SearchPaymentDocumentEnhancedTableModel;
import Utils.Utils;

public class SearchPaymentPanel extends JPanel
{
    private final JEnhancedTableScrollPanel tableScrollPanel;
    private final TableModelMaker tableModelMaker;
    private final PaymentEnhancedTableModel paymentTableModel;
    private final PaymentStatusEnhancedTableModel paymentStatusTableModel;
    private final SearchPaymentDocumentEnhancedTableModel documentTableModel;
    private final ProcessTypeEnhancedTableModel processTypesTableModel;
    private final ProcessEnhancedTableModel processTableModel;
    private final CustomerEnhancedTableModel customerTableModel;

    private final JCheckBox validatedPaymentCheckBox;
    private final JNumberField amountField;
    private final ComboBoxPanel<Integer> yearComboBox;


    public SearchPaymentPanel()
    {
        ArrayList<Integer> paymentYears;
        try
        {
            paymentYears = PaymentController.getAllPaymentYears();
        } catch (GetAllPaymentYearsException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            paymentYears = new ArrayList<>();
        }

        setLayout(new BorderLayout());
        JLabel title = new JLabel("Search Payments");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        GridBagLayoutHelper gridSearchForm = new GridBagLayoutHelper();

        validatedPaymentCheckBox = new JCheckBox();
        gridSearchForm.addField("Payment validated", validatedPaymentCheckBox);

        // Minimum Amount Field
        amountField = new JNumberField(Utils.NumberType.FLOAT, 2);
        amountField.setAllowNegative(false);
        amountField.setPlaceholder("Enter amount");
        amountField.setColumns(10);
        gridSearchForm.addField("Minimum amount", amountField);

        yearComboBox = new ComboBoxPanel<>(paymentYears, year -> Integer.toString(year));

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

        searchButton.addActionListener(e -> searchPayments());

    }

    private void searchPayments()
    {
        if(!isPaymentValid()) return;

        boolean isValidated = validatedPaymentCheckBox.isSelected();
        float minAmountValue =  amountField.getFloat();
        Integer selectedYear =  yearComboBox.getSelectedItem();

        String paymentStatus;
        if (isValidated)
        {
            paymentStatus = "Validated";
        } else {
            paymentStatus = "Not validated";
        }

        // date from the selectedYear
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, selectedYear);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date date = calendar.getTime();

        try {
            ArrayList<Payment> payment = PaymentController.searchPayments(paymentStatus, minAmountValue, date);
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

    private boolean isPaymentValid()
    {
        if(amountField.getText().isEmpty() || amountField.getFloat() < 0)
        {
            JOptionPane.showMessageDialog(null, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(yearComboBox.getSelectedItem() == null)
        {
            JOptionPane.showMessageDialog(null, "Please select a year", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}