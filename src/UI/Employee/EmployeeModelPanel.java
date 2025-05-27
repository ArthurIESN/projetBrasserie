package UI.Employee;

import Controller.Employee.EmployeeController;
import Controller.EmployeeStatus.EmployeeStatusController;
import Exceptions.Employee.GetAllEmployeesException;
import Exceptions.EmployeeStatus.GetAllEmployeeStatusesException;
import Model.Employee.Employee;
import Model.EmployeeStatus.EmployeeStatus;
import UI.Components.Fields.JDateField;
import UI.Components.Fields.JEnhancedTextField;
import UI.Components.Fields.JNumberField;
import UI.Components.Fields.SearchListPanel;
import UI.Components.GridBagLayoutHelper;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeModelPanel extends JPanel
{
    private SearchListPanel<Employee> employeeSearchField;
    private JNumberField idField;
    private final JEnhancedTextField firstNameField;
    private final JEnhancedTextField lastNameField;
    private final JDateField birthDateField;
    private final JCheckBox marriedField;
    private final JEnhancedTextField passwordField;
    private final SearchListPanel<EmployeeStatus> employeeStatusSearchField;
    private final JButton button;
    private final GridBagLayoutHelper gridEmployee;
    private final boolean isUpdateEmployee;

    public EmployeeModelPanel(boolean updateEmployee)
    {
        this.isUpdateEmployee = updateEmployee;

        ArrayList<EmployeeStatus> employeeStatuses = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();

        try
        {
            if(updateEmployee)
            {
                employees = EmployeeController.getAllEmployees();
            }

            employeeStatuses = EmployeeStatusController.getAllEmployeeStatuses();

        } catch (GetAllEmployeeStatusesException | GetAllEmployeesException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        setLayout(new BorderLayout());

        gridEmployee = new GridBagLayoutHelper();

        if(updateEmployee)
        {
            employeeSearchField = new SearchListPanel<>(employees, employee -> employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getId());
            employeeSearchField.getSearchField().setPlaceholder("Search for an employee");

            employeeSearchField.onSelectedItemChange(e ->
            {
                    for(int i = 1; i < gridEmployee.getComponents().length; i++)
                    {
                        gridEmployee.getComponents()[i].setVisible(true);
                    }
            });

            idField = new JNumberField(Utils.NumberType.INTEGER);
            idField.setEnabled(false);
            idField.setCanClear(false);
            idField.setPlaceholder("Employee Id");


            gridEmployee.addField("Search for an Employee", employeeSearchField);
            gridEmployee.addField("Employee ID", idField);
        }

        firstNameField = new JEnhancedTextField();
        firstNameField.setPlaceholder("First Name");

        lastNameField = new JEnhancedTextField();
        lastNameField.setPlaceholder("Last Name");

        birthDateField = new JDateField();
        birthDateField.setPlaceholder("Birth Date");
        birthDateField.setMaxDate(Date.from(LocalDate.now().minusYears(18).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        birthDateField.setMinDate(Date.from(LocalDate.now().minusYears(100).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        marriedField = new JCheckBox("Married");
        marriedField.setSelected(false);

        passwordField = new JEnhancedTextField();
        passwordField.setPlaceholder("Password");

        employeeStatusSearchField = new SearchListPanel<>(employeeStatuses, EmployeeStatus::getLabel);
        employeeStatusSearchField.getSearchField().setPlaceholder("Employee Status");

        button = new JButton(updateEmployee ? "Update" : "Create");

        gridEmployee.addField("First Name *", firstNameField);
        gridEmployee.addField("Last Name *", lastNameField);
        gridEmployee.addField("Birth Date *", birthDateField);
        gridEmployee.addField("Married *", marriedField);
        gridEmployee.addField("Password *", passwordField);
        gridEmployee.addField("Select an Employee Status *", employeeStatusSearchField);
        gridEmployee.addField(button);

        if(updateEmployee)
        {
            for(int i = 1; i < gridEmployee.getComponents().length; i++)
            {
                gridEmployee.getComponents()[i].setVisible(false);
            }
        }

        add(gridEmployee, BorderLayout.CENTER);
    }

    public boolean isEmployeeInvalid()
    {
        if(isUpdateEmployee && (employeeSearchField == null || employeeSearchField.getSelectedItem() == null))
        {
            JOptionPane.showMessageDialog(null, "Please select an employee", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if(isUpdateEmployee && idField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please fill in the employee id", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if(firstNameField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please fill in the first name", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if(lastNameField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please fill in the last name", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if(birthDateField.getDate() == null)
        {
            JOptionPane.showMessageDialog(this, "Please fill in the birth date", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if(passwordField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please fill in the password", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if(employeeStatusSearchField.getSelectedItem() == null)
        {
            JOptionPane.showMessageDialog(this, "Please select an employee status", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    public void onButtonClicked(ActionListener actionListener)
    {
        button.addActionListener(actionListener);
    }

    public void onSearchEmployeeChange(ActionListener actionListener)
    {
        if(employeeSearchField != null)
        {
            employeeSearchField.onSelectedItemChange(actionListener);
        }
    }

    public SearchListPanel<Employee> getEmployeeSearchField()
    {
        return employeeSearchField;
    }

    public JNumberField getIdField()
    {
        return idField;
    }

    public JEnhancedTextField getFirstNameField()
    {
        return firstNameField;
    }

    public JEnhancedTextField getLastNameField()
    {
        return lastNameField;
    }

    public JDateField getBirthDateField()
    {
        return birthDateField;
    }

    public JCheckBox getMarriedField()
    {
        return marriedField;
    }

    public JEnhancedTextField getPasswordField()
    {
        return passwordField;
    }

    public SearchListPanel<EmployeeStatus> getEmployeeStatusSearchField()
    {
        return employeeStatusSearchField;
    }
}
