package UI.Search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.SearchPaymentException;
import UI.Components.GridBagLayoutHelper;
import Controller.SearchController;
import Model.Payment;

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
        add(searchButton, BorderLayout.EAST);

        // Empty table
        JTable table = new JTable();
        table.setModel(new ItemTableModel(new ArrayList<>()));
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
        double minAmount = ((Number) amountField.getValue()).doubleValue();
        String selectedYear = (String) yearComboBox.getSelectedItem();

        try {
            ArrayList<Payment> payments = SearchController.searchPayments(isValidated, minAmount, selectedYear);
            table.setModel(new PaymentTableModel(payments));
            revalidate();
        } catch (SearchPaymentException | DatabaseConnectionFailedException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}