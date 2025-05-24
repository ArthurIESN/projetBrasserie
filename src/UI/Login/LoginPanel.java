package UI.Login;

import Controller.AppController;
import Environement.ConnexionProperties;
import Exceptions.Employee.ConnectException;
import Model.Employee.Employee;
import UI.Components.Fields.JEnhancedTextField;
import UI.Components.Fields.JNumberField;
import UI.Components.GridBagLayoutHelper;
import UI.Windows.BrasserieWindow.BrasserieHomePanel;
import UI.Windows.BrasserieWindow.BrasserieWindow;
import UI.Windows.WindowManager;
import Utils.Utils;

import javax.swing.*;

public class LoginPanel extends JPanel
{
    private final JNumberField idField;
    private final JEnhancedTextField passwordField;
    private final JCheckBox keepConnectedCheckBox;
    private boolean keepConnected;
    private final BrasserieWindow brasserieWindow;

    public LoginPanel(BrasserieWindow brasserieWindow)
    {
        this.brasserieWindow = brasserieWindow;

        if(AppController.getCurrentConnectedEmployee() != null)
        {
            WindowManager.onConnect();
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Login");
        title.setFont(title.getFont().deriveFont(20f));
        add(title);

        GridBagLayoutHelper layoutHelper = new GridBagLayoutHelper();

        idField = new JNumberField(Utils.NumberType.INTEGER);
        idField.setAllowNegative(false);
        idField.setPlaceholder("ID");

        passwordField = new JEnhancedTextField();
        passwordField.setToolTipText("Enter your password");
        passwordField.setPlaceholder("Password");

        keepConnectedCheckBox = new JCheckBox();
        keepConnectedCheckBox.addActionListener(e -> keepConnected = keepConnectedCheckBox.isSelected());

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> login());

        layoutHelper.addField("Employee ID", idField);
        layoutHelper.addField("Password", passwordField);
        layoutHelper.addField("Keep connected", keepConnectedCheckBox);
        layoutHelper.addField(loginButton);


        add(layoutHelper);
    }

    private void login()
    {
        try
        {
            Employee employee = AppController.connect(idField.getInt(), passwordField.getText());

            if (employee != null)
            {
                ConnexionProperties.setId(employee.getId());
                ConnexionProperties.setPassword(passwordField.getText());
                ConnexionProperties.setKeepConnected(keepConnected);

                ConnexionProperties.saveProperties();

                brasserieWindow.updateWindowContent(new BrasserieHomePanel());
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Invalid ID or password", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (ConnectException e)
        {
            JOptionPane.showMessageDialog(this, "Login failed: " + e.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
