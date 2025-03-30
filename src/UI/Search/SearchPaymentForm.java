package UI.Search;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.SearchPaymentException;
import UI.Components.GridBagLayoutHelper;
import Controller.SearchController;
import Model.Payment.Payment;

public class SearchPaymentForm extends JPanel
{
    public SearchPaymentForm()
    {
        // add a title
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Search Payments");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel searchForm = new JPanel(new GridBagLayout());
        GridBagLayoutHelper gridSearchForm = new GridBagLayoutHelper(searchForm);

        // Payment Validated Checkbox
        JCheckBox validatedPaymentCheckBox = new JCheckBox("Validated Payment");
        gridSearchForm.addField("Payment Validated", validatedPaymentCheckBox);

        // Minimum Amount Field
        JFormattedTextField amountField = new JFormattedTextField();
        amountField.setColumns(10);
        gridSearchForm.addField("Minimum Amount", amountField);

        // Year Selection ComboBox
        JComboBox<String> yearComboBox = new JComboBox<>(getYears());
        gridSearchForm.addField("Year", yearComboBox);

        add(searchForm, BorderLayout.CENTER);

        // add a button to search
        JButton searchButton = new JButton("Search");
        gridSearchForm.addField("Search", searchButton);

        // Empty table
        JTable table = new JTable();
        table.setModel(new PaymentTableModel(new ArrayList<>()));
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

    private void searchPayments(JCheckBox validatedPaymentCheckBox, JFormattedTextField amountField, JComboBox<String> yearComboBox, JTable table)
    {
        boolean isValidated = validatedPaymentCheckBox.isSelected();
        Number minAmountValue =  (Number) amountField.getValue();
        double minAmount = (minAmountValue != null) ? minAmountValue.doubleValue() : 0;
        String selectedYear = (String) yearComboBox.getSelectedItem();

        String paymentStatus;
        if (isValidated){
            // If the payment is validated, set the status to "Validated"
            paymentStatus = "Validated";
        } else {
            // If the payment is not validated, set the status to "Not Validated"
            paymentStatus = "Refused";
        }

        java.sql.Date sqlDate = null;
        if (selectedYear != null) {
            sqlDate = java.sql.Date.valueOf(selectedYear + "-01-01"); // Convert YYYY to SQL Date
        }

        try {
            ArrayList<Payment> payments = SearchController.searchPayments(paymentStatus, minAmount, sqlDate);
            table.setModel(new PaymentTableModel(payments));
            revalidate();
        } catch (SearchPaymentException | DatabaseConnectionFailedException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}