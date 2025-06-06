package UI.Employee;

import Controller.Employee.EmployeeController;
import Exceptions.Access.UnauthorizedAccessException;
import Exceptions.Employee.CreateEmployeeException;
import Exceptions.Employee.EmployeeException;
import Model.Employee.Employee;
import Model.Employee.MakeEmployee;

import javax.swing.*;
import java.awt.*;

public class CreateEmployeePanel extends JPanel
{
    private final EmployeeModelPanel employeeModelPanel;
    private final EmployeePanel employeePanel;

    public CreateEmployeePanel(EmployeePanel employeePanel)
    {
        this.employeePanel = employeePanel;
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Create a new employee");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        employeeModelPanel = new EmployeeModelPanel(false);

        add(employeeModelPanel, BorderLayout.CENTER);

        employeeModelPanel.onButtonClicked(e -> createEmployee());
    }

    private void createEmployee()
    {
        if(employeeModelPanel.isEmployeeInvalid()) return;
        Employee employee;
        try
        {
            employee = new Employee(
                    10,
                    employeeModelPanel.getFirstNameField().getText(),
                    employeeModelPanel.getLastNameField().getText(),
                    employeeModelPanel.getBirthDateField().getDate(),
                    employeeModelPanel.getMarriedField().isSelected(),
                    employeeModelPanel.getPasswordField().getText(),
                    employeeModelPanel.getEmployeeStatusSearchField().getSelectedItem()
            );
        }
        catch (EmployeeException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        try
        {
            EmployeeController.createEmployee(employee);
            JOptionPane.showMessageDialog(this, "Employee created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            employeePanel.navbarForceClick(0);
        } catch (CreateEmployeeException | UnauthorizedAccessException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
