package UI.Employee;

import Model.Employee.Employee;
import Model.EmployeeStatus.EmployeeStatus;
import Model.Employee.MakeEmployee;
import Controller.Employee.EmployeeController;

import javax.swing.*;
import java.awt.*;


public class UpdateEmployeePanel extends JPanel implements EmployeeObserver
{
    private final EmployeeModelPanel employeeModelPanel;

    public UpdateEmployeePanel(EmployeePanel employeePanel)
    {

        setLayout(new BorderLayout());
        JLabel title = new JLabel("Update an employee");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        employeePanel.addObserver(this);

        employeeModelPanel = new EmployeeModelPanel(true);

        employeeModelPanel.onButtonClicked(e -> updateEmployee());

        employeeModelPanel.onSearchEmployeeChange(e -> updateEmployeeSearch());

        add(employeeModelPanel);
    }

    private void updateEmployee()
    {
        if(employeeModelPanel.getEmployeeSearchField().getSelectedItem() == null)
        {
            JOptionPane.showMessageDialog(this, "Please select an employee to update", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(employeeModelPanel.isEmployeeInvalid()) return;

        EmployeeStatus employeeStatus = employeeModelPanel.getEmployeeStatusSearchField().getSelectedItem();
        Employee employee = MakeEmployee.getEmployee(
                employeeModelPanel.getIdField().getInt(),
                employeeModelPanel.getFirstNameField().getText(),
                employeeModelPanel.getLastNameField().getText(),
                employeeModelPanel.getBirthDateField().getDate(),
                employeeModelPanel.getMarriedField().isSelected(),
                employeeModelPanel.getEmployeeSearchField().getSelectedItem().getPassword(),
                employeeStatus
        );

        try
        {
            EmployeeController.updateEmployee(employee, employeeModelPanel.getPasswordField().getText());
            JOptionPane.showMessageDialog(this, "Employee updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateEmployeeSearch()
    {
        Employee employee = employeeModelPanel.getEmployeeSearchField().getSelectedItem();
        employeeModelPanel.getIdField().updateText(String.valueOf(employee.getId()));
        employeeModelPanel.getFirstNameField().updateText(employee.getFirstName());
        employeeModelPanel.getLastNameField().updateText(employee.getLastName());
        employeeModelPanel.getBirthDateField().setDate(employee.getBirthDate());
        employeeModelPanel.getMarriedField().setSelected(employee.isMarried());
        // do not set the password
        employeeModelPanel.getEmployeeStatusSearchField().setSelectedItem(employee.getEmployeeStatus());

    }

    @Override
    public void update(Employee employee)
    {
        employeeModelPanel.getEmployeeSearchField().forceSetSelectedItem(employee);
    }
}
