package UI.Search.Payment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.SearchPaymentException;
import UI.Components.Fields.JNumberField;
import UI.Components.GridBagLayoutHelper;
import Controller.SearchController;
import Model.Payment.Payment;
import UI.Components.EnhancedTable.JEnhancedTableScrollPanel;

public class SearchPaymentForm extends JPanel
{
    public SearchPaymentForm()
    {
        // add a title
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Search Payments");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        GridBagLayoutHelper gridSearchForm = new GridBagLayoutHelper();

        // Payment Validated Checkbox
        JCheckBox validatedPaymentCheckBox = new JCheckBox("Validated Payment");
        gridSearchForm.addField("Payment Validated", validatedPaymentCheckBox);

        // Minimum Amount Field
        JNumberField amountField = new JNumberField(JNumberField.NumberType.FLOAT, 2);
        amountField.setAllowNegative(false);
        amountField.setPlaceholder("Minimum Amount");
        amountField.setColumns(10);
        gridSearchForm.addField("Minimum Amount", amountField);

        // Year Selection ComboBox
        JComboBox<String> yearComboBox = new JComboBox<>(getYears());
        gridSearchForm.addField("Year", yearComboBox);

        add(gridSearchForm, BorderLayout.CENTER);

        // add a button to search
        JButton searchButton = new JButton("Search");
        gridSearchForm.addField("Search", searchButton);

        // Empty table
        JEnhancedTableScrollPanel table = new JEnhancedTableScrollPanel(new PaymentTableModel() ,this );

        add(new JScrollPane(table), BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {
            searchPayments(validatedPaymentCheckBox, amountField, yearComboBox, table);
        });
    }

    private String[] getYears() {
        String[] years = new String[10];
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        for (int i = 0; i < 10; i++) {
            years[i] = String.valueOf(currentYear - i);
        }
        return years;
    }

    private void searchPayments(JCheckBox validatedPaymentCheckBox, JNumberField amountField, JComboBox<String> yearComboBox, JEnhancedTableScrollPanel table)
    {
        boolean isValidated = validatedPaymentCheckBox.isSelected();
        Float minAmountValue =  amountField.getFloat();
        double minAmount = (minAmountValue != null) ? minAmountValue.doubleValue() : 0;
        String selectedYear = (String) yearComboBox.getSelectedItem();

        String paymentStatus;
        if (isValidated){
            paymentStatus = "Validated";
        } else {
            paymentStatus = "Refused";
        }

        java.sql.Date sqlDate = null;
        if (selectedYear != null) {
            sqlDate = java.sql.Date.valueOf(selectedYear + "-01-01"); // Convert YYYY to SQL Date
        }

        try {
            ArrayList<Payment> payments = SearchController.searchPayments(paymentStatus, minAmount, sqlDate);
            table.updateModel(new PaymentTableModel(payments));
        } catch (SearchPaymentException | DatabaseConnectionFailedException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}