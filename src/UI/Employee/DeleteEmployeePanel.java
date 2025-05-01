package UI.Employee;

import Controller.Employee.EmployeeController;
import Exceptions.Employee.GetAllEmployeesException;
import Model.EmployeeStatus.EmployeeStatus;
import UI.Components.EnhancedTable.JEnhancedTableScrollPanel;
import UI.Components.EnhancedTable.TableModelMaker;
import UI.Components.Fields.SearchListPanel;
import Model.Employee.Employee;
import UI.Components.GridBagLayoutHelper;
import UI.Models.EmployeeStatusEnhancedTableModel;
import UI.Models.EmployeeEnhancedTableModel;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DeleteEmployeePanel extends JPanel implements EmployeeObserver
{
    private final SearchListPanel<Employee> employeeSearch;

    public DeleteEmployeePanel(EmployeePanel employeePanel)
    {
        GridBagLayoutHelper layoutHelper = new GridBagLayoutHelper();

        ArrayList<Employee> employees = new ArrayList<>();

        try
        {
            employees = EmployeeController.getAllEmployees();
        }
        catch (GetAllEmployeesException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        employeePanel.addObserver(this);

        employeeSearch = new SearchListPanel<>(employees, searchEmployee -> searchEmployee.getFirstName() + "  " + searchEmployee.getLastName() + " - " + searchEmployee.getId());
        employeeSearch.setPreferredSize(new Dimension(500, employeeSearch.getPreferredSize().height));
        employeeSearch.getSearchField().setPlaceholder("Search for an employee");

        layoutHelper.addField("Employee", employeeSearch);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e ->
        {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this employee?", "Warning", JOptionPane.YES_NO_OPTION);

            if(dialogResult == JOptionPane.NO_OPTION)
            {
                return;
            }

            Employee selectedEmployee = employeeSearch.getSelectedItem();

            if(selectedEmployee != null)
            {
                try
                {
                    EmployeeController.deleteEmployee(selectedEmployee.getId());
                    JOptionPane.showMessageDialog(null, "Employee deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

                    //refresh
                    employeePanel.navbarForceClick(3);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        layoutHelper.addField(deleteButton);

        add(layoutHelper, BorderLayout.CENTER);

        TableModelMaker tableModelMaker = new TableModelMaker();
        EmployeeEnhancedTableModel employeeTableModel = new EmployeeEnhancedTableModel();
        EmployeeStatusEnhancedTableModel employeeStatusTableModel = new EmployeeStatusEnhancedTableModel();

        tableModelMaker.addTableModel(employeeTableModel);
        tableModelMaker.addTableModel(employeeStatusTableModel);

        JEnhancedTableScrollPanel tableScrollPanel = new JEnhancedTableScrollPanel(tableModelMaker, this, 4);
        tableModelMaker.setTable(tableScrollPanel);

        employeeSearch.onSelectedItemChange(
                e ->
                {
                    Employee selectedEmployee = employeeSearch.getSelectedItem();

                    ArrayList<Employee> employees1 = new ArrayList<>();
                    employees1.add(selectedEmployee);

                    ArrayList< EmployeeStatus> employeeStatuses = Utils.transformData(employees1, Employee::getEmployeeStatus);

                    employeeTableModel.setData(employees1);
                    employeeStatusTableModel.setData(employeeStatuses);

                    tableModelMaker.setTable(tableScrollPanel);

                    if(selectedEmployee != null)
                    {
                        tableScrollPanel.updateModel(tableModelMaker);
                    }
                }
        );

        add(tableScrollPanel, BorderLayout.SOUTH);
    }


    @Override
    public void update(Employee employee)
    {
        employeeSearch.forceSetSelectedItem(employee);
    }
}
