package UI.Windows.SettingsWindow;

import DataAccess.DatabaseConnexion;
import Environement.DatabaseProperties;
import UI.Components.Fields.JEnhancedTextField;
import UI.Components.Fields.JNumberField;
import UI.Components.GridBagLayoutHelper;

import javax.swing.*;

public class SettingsDatabasePanel extends JPanel
{
    public SettingsDatabasePanel(SettingsWindow settingsWindow)
    {
        GridBagLayoutHelper layoutHelper = new GridBagLayoutHelper();

        JCheckBox useOnlineDatabase = new JCheckBox();

        JEnhancedTextField databaseName = new JEnhancedTextField();
        databaseName.setPlaceholder("Database Name");

        JEnhancedTextField databaseUserName = new JEnhancedTextField();
        databaseUserName.setPlaceholder("Database User Name");

        JEnhancedTextField databasePassword = new JEnhancedTextField();
        databasePassword.setPlaceholder("Database Password");

        JEnhancedTextField databaseHost = new JEnhancedTextField();
        databaseHost.setPlaceholder("Database Host");

        JNumberField databasePort = new JNumberField(JNumberField.NumberType.INTEGER);
        databasePort.setPlaceholder("Database Port");
        databasePort.setAllowNegative(false);

        useOnlineDatabase.setSelected(DatabaseProperties.isOnlineDatabaseUsed());
        databaseName.updateText(DatabaseProperties.getDatabaseName());
        databaseUserName.updateText(DatabaseProperties.getDatabaseUsername());
        databasePassword.updateText(DatabaseProperties.getDatabasePassword());
        databaseHost.updateText(DatabaseProperties.getDatabaseHost());
        databasePort.updateText(DatabaseProperties.getDatabasePort());

        layoutHelper.addField("Use Online Database", useOnlineDatabase);
        layoutHelper.addField("Name", databaseName);
        layoutHelper.addField("User Name", databaseUserName);
        layoutHelper.addField("Password", databasePassword);
        layoutHelper.addField("Host", databaseHost);
        layoutHelper.addField("Port", databasePort);

        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(e ->
        {
           // save settings
            DatabaseProperties.setOnlineDatabaseUsed(useOnlineDatabase.isSelected());
            DatabaseProperties.setDatabaseName(databaseName.getText());
            DatabaseProperties.setDatabaseUsername(databaseUserName.getText());
            DatabaseProperties.setDatabasePassword(databasePassword.getText());
            DatabaseProperties.setDatabaseHost(databaseHost.getText());
            DatabaseProperties.setDatabasePort(databasePort.getText());

            DatabaseProperties.saveProperties();
        });

        JButton testConnectionButton = new JButton("Test Connection");
        testConnectionButton.addActionListener(e ->
        {
            saveButton.doClick();

            // test connection
            boolean success = DatabaseConnexion.testConnection();
            if (success)
            {
                JOptionPane.showMessageDialog(this, "Connection successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else
            {
                JOptionPane.showMessageDialog(this, "Connection failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        layoutHelper.addField(saveButton);
        layoutHelper.addField(testConnectionButton);

        add(layoutHelper);
    }
}
